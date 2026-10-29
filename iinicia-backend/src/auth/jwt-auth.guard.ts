
import {
  CanActivate,
  ExecutionContext,
  Injectable,
} from '@nestjs/common';

import type { Request } from 'express';
import { AuthService } from './auth.service';

type UsuarioAutenticado = {
  id: number;
  nombre_usuario: string;
  correo: string;
  rol: string;
};

type PeticionAutenticada = Request & {
  usuario?: UsuarioAutenticado;
};

@Injectable()
export class JwtAuthGuard implements CanActivate {
  constructor(
    private readonly authService: AuthService,
  ) {}

  async canActivate(
    context: ExecutionContext,
  ): Promise<boolean> {

    const peticion = context
      .switchToHttp()
      .getRequest<PeticionAutenticada>();

    const token = peticion.cookies?.iinicia_sesion;

    const usuario =
      await this.authService.obtenerPerfilDesdeToken(
        typeof token === 'string' ? token : undefined,
      );

    peticion.usuario = usuario;

    return true;
  }
}
