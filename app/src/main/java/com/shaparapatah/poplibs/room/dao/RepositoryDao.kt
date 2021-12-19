package com.shaparapatah.poplibs.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaparapatah.poplibs.room.model.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubRepository): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubRepository>) : Completable

    @Query("SELECT * FROM RoomGithubRepository")
    fun getAll(): Single<List<RoomGithubRepository>>

    @Query("SELECT * FROM RoomGithubRepository WHERE userId = :userId")
    fun getByUserId(userId: String): Single<List<RoomGithubRepository>>


}