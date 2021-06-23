package ru.bibarsov.telegram.bots.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Immutable
@ParametersAreNonnullByDefault
public class User {

    @JsonProperty("id")
    public final long id;

    @JsonProperty("is_bot")
    public final boolean isBot;

    @JsonProperty("first_name")
    public final String firstName;

    @JsonProperty("last_name")
    @Nullable
    public final String lastName;

    @JsonProperty("username")
    @Nullable
    public final String username;

    @JsonProperty("language_code")
    @Nullable
    public final String languageCode;

    @JsonCreator
    public User(
        @JsonProperty("id") long id,
        @JsonProperty("is_bot") boolean isBot,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") @Nullable String lastName,
        @JsonProperty("username") @Nullable String username,
        @JsonProperty("language_code") @Nullable String languageCode
    ) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.languageCode = languageCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
