package restaurantIOS.models.dto;

import java.io.Serializable;

public class MessageOfferDTO implements Serializable {
    private String specialMessage;

    public MessageOfferDTO(String specialMessage) {
        this.specialMessage = specialMessage;
    }

    public String getSpecialMessage() {
        return specialMessage;
    }

    public void setSpecialMessage(String specialMessage) {
        this.specialMessage = specialMessage;
    }
}
