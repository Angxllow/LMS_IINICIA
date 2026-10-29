
import {
  Body,
  Controller,
  Post,
  HttpCode,
  HttpStatus,
} from '@nestjs/common';

import { UsuariosService } from './usuarios.service';

@Controller('usuarios')
export class UsuariosController {
  constructor(
    private readonly usuariosService: UsuariosService,
  ) {}

  @Post('registro')
  @HttpCode(HttpStatus.CREATED)
  async registrar(
    @Body()
    datos: {
      nombre_usuario: string;
      correo: string;
      contrasena: string;
    },
  ) {
    const usuario = await this.usuariosService.registrar(datos);

    return {
      mensaje: 'Usuario registrado correctamente',
      usuario,
    };
  }
}
