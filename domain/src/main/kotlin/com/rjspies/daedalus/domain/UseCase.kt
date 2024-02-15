package com.rjspies.daedalus.domain

public interface UseCase<UseCaseType> {
    public fun perform(): UseCaseType
}
