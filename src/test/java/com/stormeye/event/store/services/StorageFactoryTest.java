package com.stormeye.event.store.services;

import com.casper.sdk.model.event.DataType;
import com.casper.sdk.model.event.blockadded.BlockAdded;
import com.stormeye.event.store.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * @author ian@meywood.com
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class StorageFactoryTest {

    @Autowired
    private StorageFactory storageFactory;

    @Test
    void testBlockAddedStorageFactory() {
        final StorageService<BlockAdded, Block>storageService = storageFactory.getStorageService(DataType.BLOCK_ADDED);
        assertThat(storageService, instanceOf(BlockAddedStorageService.class));
    }
}