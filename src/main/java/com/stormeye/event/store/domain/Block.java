package com.stormeye.event.store.domain;

import com.casper.sdk.model.common.Digest;
import com.casper.sdk.model.key.PublicKey;
import com.stormeye.event.store.conveter.DigestConverter;
import com.stormeye.event.store.conveter.PublicKeyConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * The domain object for a Block
 * @author ian@meywood.com
 */
@Getter
@Setter
 @AllArgsConstructor
@NoArgsConstructor
@Entity
public class Block extends AbstractPersistable<Long> {
    private Long id;
    @Convert(converter = DigestConverter.class)
    @Column
    private Digest blockHash;
    @Convert(converter = DigestConverter.class)
    @Column
    private Digest parentHash;
    /** ISO Date */
    @Column
    private Date timestamp;
    @Column
    @Convert(converter = DigestConverter.class)
    private Digest state;
    @Column
    private long deployCount;
    @Column
    private long transferCount;
    @Column
    private long eraId;
    @Column
    @Convert(converter = PublicKeyConverter.class)
    private PublicKey proposer;
}
