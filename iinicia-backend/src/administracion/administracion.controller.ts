
import {
  Controller,
  Get,
  UseGuards,
} from '@nestjs/common';

import { AdministracionService } from './administracion.service';

import { JwtAuthGuard } from '../auth/jwt-auth.guard';
import { RolesGuard } from '../auth/roles.guard';
import { Roles } from '../auth/roles.decorator';

@Controller('administracion')
@UseGuards(JwtAuthGuard, RolesGuard)
export class AdministracionController {
  constructor(
    private readonly administracionService: AdministracionService,
  ) {}

  // CONSULTAR USUARIOS - SOLO ADMINISTRADORES
  @Get('usuarios')
  @Roles('administrador')
  async listarUsuarios() {
    return this.administracionService.listarUsuarios();
  }
}
