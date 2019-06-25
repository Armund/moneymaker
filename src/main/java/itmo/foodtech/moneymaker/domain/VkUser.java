package itmo.foodtech.moneymaker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VkUser {

    private String vkId;

    private String firstName;

    private String lastName;

    private String bdate;

    private String city;

    private int gender;

    private int timezone;
}
