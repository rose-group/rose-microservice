/*
 * Copyright © 2025 rosestack.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cc.chensoul.jpa.support;

import cc.chensoul.jpa.converter.InstantLongConverter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author <a href="mailto:ichensoul@gmail.com">chensoul</a>
 * @since 0.0.1
 */
@MappedSuperclass
@Data
public abstract class BaseJpaAggregate extends AbstractAggregateRoot<BaseJpaAggregate> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Convert(converter = InstantLongConverter.class)
    @Setter(AccessLevel.PROTECTED)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @Convert(converter = InstantLongConverter.class)
    @Setter(AccessLevel.PROTECTED)
    private Instant updatedAt;

    @Version
    @Column(name = "version")
    @Setter(AccessLevel.PRIVATE)
    private Integer version;

    @PrePersist
    public void prePersist() {
        this.setCreatedAt(Instant.now());
        this.setUpdatedAt(Instant.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(Instant.now());
    }
}
