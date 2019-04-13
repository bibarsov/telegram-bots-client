package bibarsov.telegram.bots.client.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class UserInfo {

    public final long id;
    public final boolean isBot;
    public final String firstName;
    @Nullable
    public final String lastName;
    @Nullable
    public final String username;
    @Nullable
    public final String languageCode;


    public UserInfo(
            long id,
            boolean isBot,
            String firstName,
            @Nullable String lastName,
            @Nullable String username,
            @Nullable String languageCode
    ) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.languageCode = languageCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
