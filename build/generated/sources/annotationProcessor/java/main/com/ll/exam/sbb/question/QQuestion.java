package com.ll.exam.sbb.question;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = 614438085L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final ListPath<com.ll.exam.sbb.answer.Answer, com.ll.exam.sbb.answer.QAnswer> answerList = this.<com.ll.exam.sbb.answer.Answer, com.ll.exam.sbb.answer.QAnswer>createList("answerList", com.ll.exam.sbb.answer.Answer.class, com.ll.exam.sbb.answer.QAnswer.class, PathInits.DIRECT2);

    public final com.ll.exam.sbb.user.QSiteUser author;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final StringPath subject = createString("subject");

    public final SetPath<com.ll.exam.sbb.user.SiteUser, com.ll.exam.sbb.user.QSiteUser> voter = this.<com.ll.exam.sbb.user.SiteUser, com.ll.exam.sbb.user.QSiteUser>createSet("voter", com.ll.exam.sbb.user.SiteUser.class, com.ll.exam.sbb.user.QSiteUser.class, PathInits.DIRECT2);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.ll.exam.sbb.user.QSiteUser(forProperty("author")) : null;
    }

}

