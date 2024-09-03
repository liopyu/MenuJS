package net.liopyu.menujs.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import static net.liopyu.menujs.MenuJS.LOGGER;
public class MenuJSHelperClass {
    public static final Set<String> errorMessagesLogged = new HashSet<>();
    public static final Set<String> warningMessagesLogged = new HashSet<>();
    public static final Set<String> infoMessagesLogged = new HashSet<>();

    public static void logErrorMessageOnce(String errorMessage) {
        if (!errorMessagesLogged.contains(errorMessage)) {
            LOGGER.error("[Dynamic Structures]: " + errorMessage);
            errorMessagesLogged.add(errorMessage);
        }
    }

    public static void logErrorMessage(String errorMessage) {
        LOGGER.error("[Dynamic Structures]: " + errorMessage);
    }

    public static void logWarningMessageOnce(String errorMessage) {
        if (!warningMessagesLogged.contains(errorMessage)) {
            LOGGER.warn("[Dynamic Structures]: " + errorMessage);
            warningMessagesLogged.add(errorMessage);
        }
    }

    public static void logWarningMessageOnceDev(String errorMessage) {
        if (!FMLEnvironment.production && !warningMessagesLogged.contains(errorMessage)) {
            LOGGER.warn("[Dynamic Structures]: " + errorMessage);
            warningMessagesLogged.add(errorMessage);
        }
    }

    public static void logWarningMessage(String errorMessage) {
        LOGGER.warn("[Dynamic Structures]: " + errorMessage);
    }

    public static void logErrorMessageCatchable(String errorMessage, Throwable e) {
        LOGGER.error("[Dynamic Structures]: " + errorMessage, e);
    }

    public static void logErrorMessageOnceCatchable(String errorMessage, Throwable e) {
        if (!errorMessagesLogged.contains(errorMessage)) {
            LOGGER.error("[Dynamic Structures]: " + errorMessage, e);
            errorMessagesLogged.add(errorMessage);
        }
    }

    public static void logInfoMessageOnce(String info) {
        if (!infoMessagesLogged.contains(info)) {
            LOGGER.info("[Dynamic Structures]: " + info);
            infoMessagesLogged.add(info);
        }
    }

    public static void logInfoMessageOnceDev(String info) {
        if (!FMLEnvironment.production && !infoMessagesLogged.contains(info)) {
            LOGGER.info("[Dynamic Structures]: " + info);
            infoMessagesLogged.add(info);
        }
    }

    public static void logInfoMessage(String info) {
        LOGGER.info("[Dynamic Structures]: " + info);
    }

    public static void logInfoMessageDev(String info) {
        if (!FMLEnvironment.production) {
            LOGGER.info("[Dynamic Structures]: " + info);
        }
    }

    public static <T> boolean consumerCallback(Consumer<T> consumer, T value, String errorMessage) {
        try {
            consumer.accept(value);
        } catch (Throwable e) {
            logErrorMessageOnceCatchable(errorMessage, e);
            return false;
        }
        return true;
    }

    public static Object convertObjectToDesired(Object input, String outputType) {
        return switch (outputType.toLowerCase()) {
            case "integer" -> convertToInteger(input);
            case "double" -> convertToDouble(input);
            case "float" -> convertToFloat(input);
            case "boolean" -> convertToBoolean(input);
            case "interactionresult" -> convertToInteractionResult(input);
            case "resourcelocation" -> convertToResourceLocation(input);
            case "itemstack" -> convertToItemStack(input);
            default -> input;
        };
    }
    public static ItemStack convertToItemStack(Object input) {
        if (input instanceof ItemStack) {
            return (ItemStack) input;
        } else if (input instanceof String string) {
            var itemStack = ForgeRegistries.ITEMS.getValue(new ResourceLocation(string));
            if (itemStack != null) {
                return itemStack.getDefaultInstance();
            }
        }
        return null;
    }
    public static ResourceLocation convertToResourceLocation(Object input) {
        if (input instanceof ResourceLocation) {
            return (ResourceLocation) input;
        } else if (input instanceof String) {
            return new ResourceLocation((String) input);
        }
        return null;
    }

    public static InteractionResult convertToInteractionResult(Object input) {
        if (input instanceof InteractionResult) {
            return (InteractionResult) input;
        } else if (input instanceof String) {
            String stringValue = ((String) input).toLowerCase();
            switch (stringValue) {
                case "success":
                    return InteractionResult.SUCCESS;
                case "consume":
                    return InteractionResult.CONSUME;
                case "pass":
                    return InteractionResult.PASS;
                case "fail":
                    return InteractionResult.FAIL;
                case "consume_partial":
                    return InteractionResult.CONSUME_PARTIAL;
            }
        }
        return null;
    }

    public static Boolean convertToBoolean(Object input) {
        if (input instanceof Boolean) {
            return (Boolean) input;
        } else if (input instanceof String) {
            String stringValue = ((String) input).toLowerCase();
            if ("true".equals(stringValue)) {
                return true;
            } else if ("false".equals(stringValue)) {
                return false;
            }
        }
        return null;
    }

    public static Integer convertToInteger(Object input) {
        if (input instanceof Integer) {
            return (Integer) input;
        } else if (input instanceof Double || input instanceof Float) {
            return ((Number) input).intValue();
        } else {
            return null;
        }
    }

    public static Double convertToDouble(Object input) {
        if (input instanceof Double) {
            return (Double) input;
        } else if (input instanceof Integer || input instanceof Float) {
            return ((Number) input).doubleValue();
        } else {
            return null;
        }
    }

    public static Float convertToFloat(Object input) {
        if (input instanceof Float) {
            return (Float) input;
        } else if (input instanceof Integer || input instanceof Double) {
            return ((Number) input).floatValue();
        } else {
            return null;
        }
    }
}
