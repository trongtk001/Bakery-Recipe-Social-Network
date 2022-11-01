package com.example.bakeryrecipe.mapper;

import com.example.bakeryrecipe.dto.MessageDTO;
import com.example.bakeryrecipe.entity.Message;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper extends BaseMapper<Message, MessageDTO>{
    public MessageMapper(ModelMapper modelMapper) {
        super(modelMapper, Message.class, MessageDTO.class);
    }

    public static PropertyMap<Message, MessageDTO> entityToDTOPropertyMap = new PropertyMap<Message, MessageDTO>() {
        @Override
        protected void configure() {
            map(source.getMemberSender().getId(), destination.getSenderID());
            map(source.getMemberReceiver().getId(), destination.getReceiverID());
        }
    };

    public static PropertyMap<MessageDTO, Message> dtoToEntityPropertyMap = new PropertyMap<MessageDTO, Message>() {
        @Override
        protected void configure() {
            map(source.getSenderID(), destination.getMemberSender().getId());
            map(source.getReceiverID(), destination.getMemberReceiver().getId());
        }
    };
}
