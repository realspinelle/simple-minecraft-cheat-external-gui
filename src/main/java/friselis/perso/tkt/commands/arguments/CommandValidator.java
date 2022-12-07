package friselis.perso.tkt.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;

import java.util.function.Predicate;

public class CommandValidator {
    public static Predicate<String> getCommandValidator(CommandManager.CommandParser parser) {
        return (string) -> {
            try {
                parser.parse(new StringReader(string));
                return true;
            } catch (CommandSyntaxException var3) {
                return false;
            }
        };
    }
}
