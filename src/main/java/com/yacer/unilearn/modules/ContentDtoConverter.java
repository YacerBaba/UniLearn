package com.yacer.unilearn.modules;

import com.yacer.unilearn.entities.Content;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ContentDtoConverter {

    public ContentDTO convertToDto(Content content) {
        return new ContentDTO(
                content.getContentId(),
                content.getContent_url()
        );
    }

    public List<ContentDTO> convertContentsToDTOsList(List<Content> contents) {
        var result = new LinkedList<ContentDTO>();
        for (var content : contents) {
            result.add(convertToDto(content));
        }
        return result;
    }


}
