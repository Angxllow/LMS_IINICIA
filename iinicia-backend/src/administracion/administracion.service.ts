
import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';

import { Usuario } from '../usuarios/usuario.entity';

@Injectable()
export class AdministracionService {
  constructor(
    @InjectRepository(Usuario)
    private readonly usuariosRepository: Repository<Usuario>,
  ) {}

  // CONSULTAR LOS USUARIOS REGISTRADOS
  async listarUsuarios() {
    const usuarios = await this.usuariosRepository.find({
      select: {
        id: true,
        nombre_usuario: true,
        correo: true,
        rol: true,
        creado_en: true,
      },
      order: {
        id: 'DESC',
      },
      take: 50,
    });

    return {
      mensaje: 'Usuarios consultados correctamente',
      total_mostrados: usuarios.length,
      usuarios,
    };
  }
}
