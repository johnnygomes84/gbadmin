package com.mlg.gbadmin.model

import com.mlg.gbadmin.enums.ClassTypeEnum
import com.mlg.gbadmin.enums.RankEnum
import com.mlg.gbadmin.enums.StatusEnum
import groovy.transform.Canonical
import groovyjarjarantlr4.v4.runtime.misc.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document


@Canonical
@Document
class Student implements Serializable {
    private static final long serialVersionUID = 1L

    @Id
    @NotNull
    String id
    @NotNull
    @Indexed
    Long studentNumber
    @NotNull
    String name
    @NotNull
    String lastName
    @NotNull
    Date birthday
    @NotNull
    RankEnum rank
    @NotNull
    Integer degree
    @NotNull
    ClassTypeEnum classType
    @NotNull
    StatusEnum status
}
