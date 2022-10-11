package com.stormeye.event.store.services;

import com.casper.sdk.model.common.Digest;
import com.casper.sdk.model.key.PublicKey;
import com.stormeye.event.store.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

/**
 * @author ian@meywood.com
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BlockRepositoryTest {

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void save() throws NoSuchAlgorithmException {

        final Date timestamp = new Date();

        final PublicKey proposer = PublicKey.fromTaggedHexString("017d96b9a63abcb61c870a4f55187a0a7ac24096bdb5fc585c12a686a4d892009e");
        final Block block = new Block(null,
                new Digest("5a91486c973deea304e26138206723278d9d269f4fe03bfc9e5fdb93e927236e"),
                new Digest("6c6aa63fb4e3e10f964e3be535d29b023902ace44483409e932ffd3cadfbf47b"),
                timestamp,
                new Digest("99a6cae10c5ab5b528e968378ead4bc8ef56a6613227e85e28845d9e398103ae"),
                1,
                2,
                3L,
                proposer
        );

        final Block saved = blockRepository.save(block);
        assertThat(saved.getId(), is(greaterThan(0L)));


        final Optional<Block> byId = blockRepository.findById(1L);
        assertThat(byId.isPresent(), is(true));

        final Block found = byId.get();
        assertThat(found.getId(), is(1L));
        assertThat(found.getBlockHash(), is(new Digest("5a91486c973deea304e26138206723278d9d269f4fe03bfc9e5fdb93e927236e")));
        assertThat(found.getParentHash(), is(new Digest("6c6aa63fb4e3e10f964e3be535d29b023902ace44483409e932ffd3cadfbf47b")));
        assertThat(found.getTimestamp().getTime(), is(timestamp.getTime()));
        assertThat(found.getState(), is(new Digest("99a6cae10c5ab5b528e968378ead4bc8ef56a6613227e85e28845d9e398103ae")));
        assertThat(found.getDeployCount(), is(1L));
        assertThat(found.getTransferCount(), is(2L));
        assertThat(found.getEraId(), is(3L));
        assertThat(found.getProposer(), is(proposer));
    }
}