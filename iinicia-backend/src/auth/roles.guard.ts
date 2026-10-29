
import {
  CanActivate,
  ExecutionContext,
  Injectable,
} from '@nestjs/common';

import { Reflector } from '@nestjs/core';
import type { Request } from 'express';

import type { RolUsuario } from '../usuarios/usuario.entity';
import { ROLES_KEY } from './roles.decorator';

type PeticionConUsuario = Request & {
  usuario?: {
    id: number;
    nombre_usuario: string;
    correo: string;
    rol: RolUsuario;
  };
};

@Injectable()
export class RolesGuard implements CanActivate {
  constructor(
    private readonly reflector: Reflector,
  ) {}

  canActivate(context: ExecutionContext): boolean {
    const rolesPermitidos =
      this.reflector.getAllAndOverride<RolUsuario[]>(
        ROLES_KEY,
        [
          context.getHandler(),
          context.getClass(),
        ],
      );

    // Si no se especifican roles,
    // este guard no aplica restricciones adicionales.
    if (!rolesPermitidos || rolesPermitidos.length === 0) {
      return true;
    }

    const peticion = context
      .switchToHttp()
      .getRequest<PeticionConUsuario>();

    const usuario = peticion.usuario;

    if (!usuario) {
      return false;
    }

    return rolesPermitidos.includes(usuario.rol);
  }
}
