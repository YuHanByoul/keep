package com.kbrainc.plum.sample.tag;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class HelloDialect extends AbstractProcessorDialect {
    public HelloDialect() {
        super("Hello Dialect", // Dialect name
                "hello", // Dialect prefix (hello:*)
                1000); // Dialect precedence
    }

    @Override
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new SayToAttributeTagProcessor(dialectPrefix));
        return processors;
    }

}
