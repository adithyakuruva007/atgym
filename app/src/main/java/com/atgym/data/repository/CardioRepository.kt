package com.atgym.data.repository

import com.atgym.data.database.dao.CardioLogDao
import com.atgym.data.database.entity.CardioLog
import kotlinx.coroutines.flow.Flow

class CardioRepository(private val cardioLogDao: CardioLogDao) {
    val allLogs: Flow<List<CardioLog>> = cardioLogDao.getAllLogs()
    fun getLogsForDate(date: String): Flow<List<CardioLog>> = cardioLogDao.getLogsForDate(date)
    suspend fun logCardio(log: CardioLog) = cardioLogDao.insert(log)
    fun getTotalMinutesSince(startDate: String): Flow<Int?> = cardioLogDao.getTotalMinutesSince(startDate)
    fun getTotalCaloriesSince(startDate: String): Flow<Int?> = cardioLogDao.getTotalCaloriesSince(startDate)
    suspend fun delete(log: CardioLog) = cardioLogDao.delete(log)
}
