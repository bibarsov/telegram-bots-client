package bibarsov.telegram.bots.client.value;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@Immutable
@ParametersAreNonnullByDefault
public enum ChatType {

    PRIVATE,
    GROUP,
    SUPERGROUP,
    CHANNEL
}
