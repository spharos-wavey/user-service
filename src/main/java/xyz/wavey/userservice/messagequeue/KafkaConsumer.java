package xyz.wavey.userservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.model.User;
import xyz.wavey.userservice.repository.UserRepo;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private final UserRepo userRepo;

    @KafkaListener(topics = "user-reward")
    public void updateReward(String message) {

        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(message, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        User user = userRepo.findByUuid((String) map.get("uuid")).get();
        if(user.getReward() != null){
            user.setReward(user.getReward() - (Integer) map.get("reward"));
            userRepo.save(user);
        }
    }
}
