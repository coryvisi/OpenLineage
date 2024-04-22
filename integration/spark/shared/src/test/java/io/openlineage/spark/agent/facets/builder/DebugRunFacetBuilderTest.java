/*
/* Copyright 2018-2024 contributors to the OpenLineage project
/* SPDX-License-Identifier: Apache-2.0
*/

package io.openlineage.spark.agent.facets.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.openlineage.spark.api.OpenLineageContext;
import io.openlineage.spark.api.SparkOpenLineageConfig;
import org.apache.spark.SparkContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DebugRunFacetBuilderTest {

  private static OpenLineageContext openLineageContext =
      mock(OpenLineageContext.class, RETURNS_DEEP_STUBS);
  private static SparkContext sparkContext = mock(SparkContext.class);
  private static DebugRunFacetBuilder builder = new DebugRunFacetBuilder(openLineageContext);
  private static SparkOpenLineageConfig config = new SparkOpenLineageConfig();

  @BeforeAll
  static void setup() {
    builder = new DebugRunFacetBuilder(openLineageContext);
    when(openLineageContext.getOpenLineageConfig()).thenReturn(config);
  }

  @Test
  void testIsDefinedAtWhenDebugEnabled() {
    config.setDebugFacet("enabled");
    assertThat(builder.isDefinedAt(mock(Object.class))).isTrue();
  }

  @Test
  void testIsDefinedAtWhenDebugDisabled() {
    config.setDebugFacet("disabled");
    assertThat(builder.isDefinedAt(mock(Object.class))).isFalse();
  }
}
