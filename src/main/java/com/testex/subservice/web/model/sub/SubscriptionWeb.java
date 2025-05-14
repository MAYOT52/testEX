package com.testex.subservice.web.model.sub;

import com.testex.subservice.shared.Default;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubscriptionWeb {
    private UUID subID;
    private String subName;

    public SubscriptionWeb() {}

    @Default
    public SubscriptionWeb(UUID subID, String subName) {
        this.subID = subID;
        this.subName = subName;
    }
}
