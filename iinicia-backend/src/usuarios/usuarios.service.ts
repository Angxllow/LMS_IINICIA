
import {
  Injectable,
  BadRequestException,
  ConflictException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository, QueryFailedError } from 'typeorm';
import * as bcrypt from 'bcryptjs';
import { Usuario } from './usuario.entity';

@Injectable()
export class UsuariosService {
  constructor(
    @InjectRepository(Usuario)
    private readonly usuariosRepository: Repository<Usuario>,
  ) {}

  async registrar(datos: {
    nombre_usuario: string;
    correo: string;
    contrasena: string;
  }) {
    const nombre_usuario = datos.nombre_usuario?.trim();
    const correo = datos.correo?.trim().toLowerCase();
    const contrasena = datos.contrasena;

    if (!nombre_usuario || !correo || !contrasena) {
      throw new BadRequestException(
        'Todos los campos son obligatorios',
      );
    }

    if (
      nombre_usuario.length < 3 ||
      nombre_usuario.length > 100 ||
      correo.length > 150 ||
      !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo)
    ) {
      throw new BadRequestException(
        'Nombre de usuario o correo no válido',
      );
    }

    if (
      contrasena.length < 8 ||
      Buffer.byteLength(contrasena, 'utf8') > 72
    ) {
      throw new BadRequestException(
        'La contraseña debe tener al menos 8 caracteres y un máximo de 72 bytes',
      );
    }

    const contrasena_hash = await bcrypt.hash(contrasena, 12);

    const usuario = this.usuariosRepository.create({
      nombre_usuario,
      correo,
      contrasena_hash,
      rol: 'aspirante',
    });

    try {
      const guardado = await this.usuariosRepository.save(usuario);

      return {
        id: guardado.id,
        nombre_usuario: guardado.nombre_usuario,
        correo: guardado.correo,
        rol: guardado.rol,
        creado_en: guardado.creado_en,
      };
    } catch (error) {
      if (
        error instanceof QueryFailedError &&
        (error.driverError as { code?: string }).code ===
          'ER_DUP_ENTRY'
      ) {
        throw new ConflictException(
          'El usuario o correo ya está registrado',
        );
      }

      throw error;
    }
  }
}
