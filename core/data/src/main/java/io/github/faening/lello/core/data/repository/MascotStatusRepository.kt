package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MascotStatusDao
import io.github.faening.lello.core.database.model.mascot.toEntity
import io.github.faening.lello.core.database.model.mascot.toModel
import io.github.faening.lello.core.domain.repository.MascotStatusResource
import io.github.faening.lello.core.model.mascot.MascotStatus
import javax.inject.Inject

class MascotStatusRepository @Inject constructor(
    private val dao: MascotStatusDao
) : MascotStatusResource<MascotStatus> {

    override suspend fun getStatus(): MascotStatus? {
        return dao.getStatus()?.toModel()
    }

    override suspend fun insertOrUpdate(status: MascotStatus) {
        dao.insertOrUpdate(status.toEntity())
    }
}

