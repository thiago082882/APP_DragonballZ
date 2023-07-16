package com.thiago.dragonballzapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.thiago.dragonballzapp.domain.repository.DataStoreOperation
import com.thiago.dragonballzapp.util.Constants.PREFERENCE_KEY
import com.thiago.dragonballzapp.util.Constants.PREFERENCE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreOperationImpl(context: Context) : DataStoreOperation {


    private object PreferenceKey {
        val onBoardingkEY = booleanPreferencesKey(name = PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore
    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.onBoardingkEY] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exeption ->
                if (exeption is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exeption
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKey.onBoardingkEY] ?: false
                onBoardingState

            }
    }
}