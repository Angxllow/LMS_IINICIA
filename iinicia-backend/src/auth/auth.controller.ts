
import {
  Body,
  Controller,
  Get,
  Post,
  HttpCode,
  HttpStatus,
  Req,
  Res,
  UseGuards,
} from '@nestjs/common';

import type { Request, Response } from 'express';

import { AuthService } from './auth.service';
import { JwtAuthGuard } from './jwt-auth.guard';
import { RolesGuard } from './roles.guard';
import { Roles } from './roles.decorator';

@Controller('auth')
export class AuthController {
  constructor(
    private readonly authService: AuthService,
  ) {}

  // INICIAR SESIÓN
  @Post('login')
  @HttpCode(HttpStatus.OK)
  async iniciarSesion(
    @Body()
    datos: {
      identificador?: string;
      contrasena?: string;
    },
    @Res({ passthrough: true }) respuesta: Response,
  ) {
    const usuario = await this.authService.validarCredenciales(
      datos?.identificador ?? '',
      datos?.contrasena ?? '',
    );

    const token = await this.authService.generarToken(usuario);

    respuesta.cookie('iinicia_sesion', token, {
      httpOnly: true,
      secure: process.env.NODE_ENV === 'production',
      sameSite: 'lax',
      maxAge: 15 * 60 * 1000,
      path: '/',
    });
return {
      mensaje: 'Credenciales correctas',
      usuario,
      access_token: token, // <-- Agrega esta línea para tu app de Android
    };
  }

  // PERFIL PROTEGIDO
  @Get('perfil')
  @UseGuards(JwtAuthGuard)
  async obtenerPerfil(
    @Req() peticion: Request,
  ) {
    const token = peticion.cookies?.iinicia_sesion;

    return this.authService.obtenerPerfilDesdeToken(
      typeof token === 'string' ? token : undefined,
    );
  }

  // RUTA DE PRUEBA EXCLUSIVA PARA ADMINISTRADORES
  @Get('admin/prueba')
  @UseGuards(JwtAuthGuard, RolesGuard)
  @Roles('administrador')
  comprobarAdministrador() {
    return {
      mensaje: 'Acceso de administrador autorizado',
    };
  }
// RUTA DE PRUEBA PARA ASPIRANTES
@Get('aspirante/prueba')
@UseGuards(JwtAuthGuard, RolesGuard)
@Roles('aspirante')
comprobarAspirante() {
  return {
    mensaje: 'Acceso de aspirante autorizado',
  };
}

  // CERRAR SESIÓN
  
  @Post('logout')
  @HttpCode(HttpStatus.OK)
  cerrarSesion(
    @Res({ passthrough: true }) respuesta: Response,
  ) {
    respuesta.clearCookie('iinicia_sesion', {
      httpOnly: true,
      secure: process.env.NODE_ENV === 'production',
      sameSite: 'lax',
      path: '/',
    });

    return {
      mensaje: 'Sesión cerrada correctamente',
    };
  }
}
