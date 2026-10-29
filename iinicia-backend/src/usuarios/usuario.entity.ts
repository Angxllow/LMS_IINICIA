
import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
} from 'typeorm';

export type RolUsuario =
  | 'administrador'
  | 'profesor'
  | 'estudiante'
  | 'coordinador'
  | 'aspirante';

@Entity('usuarios')
export class Usuario {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column({
    type: 'varchar',
    length: 100,
    unique: true,
  })
  nombre_usuario!: string;

  @Column({
    type: 'varchar',
    length: 150,
    unique: true,
  })
  correo!: string;

  @Column({
    type: 'varchar',
    length: 255,
    select: false,
  })
  contrasena_hash!: string;

  @Column({
    type: 'enum',
    enum: [
      'administrador',
      'profesor',
      'estudiante',
      'coordinador',
      'aspirante',
    ],
    default: 'aspirante',
  })
  rol!: RolUsuario;

  @CreateDateColumn({
    type: 'timestamp',
  })
  creado_en!: Date;
}
