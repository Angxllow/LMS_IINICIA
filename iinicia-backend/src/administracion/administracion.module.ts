
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';

import { Usuario } from '../usuarios/usuario.entity';
import { AuthModule } from '../auth/auth.module';

import { AdministracionService } from './administracion.service';
import { AdministracionController } from './administracion.controller';

@Module({
  imports: [
    TypeOrmModule.forFeature([Usuario]),
    AuthModule,
  ],

  controllers: [
    AdministracionController,
  ],

  providers: [
    AdministracionService,
  ],

  exports: [
    AdministracionService,
  ],
})
export class AdministracionModule {}
