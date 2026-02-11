package com.yupi.yuaiagent.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责读取所有 Markdown 文档并转换为 Document 列表。
 */
@Component
@Slf4j
public class LoveAppDocumentLoader {

    private final ResourcePatternResolver resourcePatternResolver;

    public LoveAppDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    public List<Document> loadMarkdowns() {
        List<Document> allDocuments = new ArrayList<>();
        try {
            //获取所有 Markdown 文档
            Resource[] resources = resourcePatternResolver.getResources("classpath*:document/*.md");
            //遍历所有资源
            for (Resource resource : resources) {
                String fileName = resource.getFilename();
                //MarkdownDocumentReaderConfig是springAI提供的，用于配置Markdown文档的读取行为。
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        //是否将 Markdown 文档中的水平规则（例如：---）创建为文档
                        .withHorizontalRuleCreateDocument(true)
                        //是否将 Markdown 文档中的代码块创建为文档
                        .withIncludeCodeBlock(false)
                        //是否将 Markdown 文档中的引用创建为文档
                        .withIncludeBlockquote(false)
                        //是否将 Markdown 文档中的标题创建为文档
                        .withAdditionalMetadata("filename", fileName)
                        .build();
                //创建 MarkdownDocumentReader 对象
                MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
                allDocuments.addAll(reader.get());
            }
        } catch (IOException e) {
            log.error("Markdown 文档加载失败", e);
        }
        return allDocuments;
    }
}

















