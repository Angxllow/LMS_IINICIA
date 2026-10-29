
import {
  Injectable,
  UnauthorizedException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { JwtService } from '@nestjs/jwt';
import { Repository } from 'typeorm';
import * as bcrypt from 'bcryptjs';

import { Usuario } from '../usuarios/usuario.entity';

@Injectable()
export class AuthService {
  constructor(
    @InjectRepository(Usuario)
    private readonly usuariosRepository: Repository<Usuario>,

    private readonly jwtService: JwtService,
  ) {}

  async validarCredenciales(
    identificador: string,
    contrasena: string,
  ) {
    if (
      typeof identificador !== 'string' ||
      typeof contrasena !== 'string' ||
      !identificador.trim() ||
      !contrasena
    ) {
      throw new UnauthorizedException(
        'Usuario o contraseña incorrectos',
      );
    }

    const valor = identificador.trim();

    const usuario = await this.usuariosRepository.findOne({
      where: [
        { nombre_usuario: valor },
        { correo: valor.toLowerCase() },
      ],
      select: {
        id: true,
        nombre_usuario: true,
        correo: true,
        rol: true,
        contrasena_hash: true,
      },
    });

    if (!usuario) {
      throw new UnauthorizedException(
        'Usuario o contraseña incorrectos',
      );
    }

    const claveCorrecta = await bcrypt.compare(
      contrasena,
      usuario.contrasena_hash,
    );

    if (!claveCorrecta) {
      throw new UnauthorizedException(
        'Usuario o contraseña incorrectos',
      );
    }

    return {
      id: usuario.id,
      nombre_usuario: usuario.nombre_usuario,
      correo: usuario.correo,
      rol: usuario.rol,
    };
  }

  async generarToken(usuario: {
    id: number;
    rol: string;
  }) {
    return this.jwtService.signAsync({
      sub: usuario.id,
      rol: usuario.rol,
    });
  }
  
  async obtenerPerfilDesdeToken(token?: string) {
    if (!token) {
      throw new UnauthorizedException('Sesión no válida');
    }

    try {
      const datos = await this.jwtService.verifyAsync<{
        sub: number;
      }>(token);

      if (!Number.isInteger(datos.sub) || datos.sub <= 0) {
        throw new Error('Token no válido');
      }

      const usuario = await this.usuariosRepository.findOne({
        where: { id: datos.sub },
        select: {
          id: true,
          nombre_usuario: true,
          correo: true,
          rol: true,
        },
      });

      if (!usuario) {
        throw new UnauthorizedException('Usuario no encontrado');
      }

      return usuario;
    } catch {
      throw new UnauthorizedException(
        'Sesión inválida o expirada',
      );
    }
  }

}
