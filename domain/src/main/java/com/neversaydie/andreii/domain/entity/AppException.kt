package com.neversaydie.andreii.domain.entity

data class AppException(val errorType: AppErrorType):Exception(),DomainEntity {

}