package net.liopyu.menujs.client.widgets;

import net.liopyu.menujs.builders.AbstractContainerBuilder;
import net.liopyu.menujs.builders.widget.AbstractWidgetBuilder;
import net.liopyu.menujs.util.ContextUtils;
import net.liopyu.menujs.util.MenuJSHelperClass;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

import static net.liopyu.menujs.util.MenuJSHelperClass.consumerCallback;
import static net.liopyu.menujs.util.MenuJSHelperClass.convertObjectToDesired;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@OnlyIn(Dist.CLIENT)
public class AbstractWidgetJS extends AbstractWidget {

    private final AbstractWidgetBuilder builder;
    private final AbstractContainerBuilder<?> menuBuilder;

    public AbstractWidgetJS(AbstractWidgetBuilder builder, AbstractContainerBuilder<?> menuBuilder, int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
        this.builder = builder;
        this.menuBuilder = menuBuilder;
        if (builder.onInit != null) {
            var context = new ContextUtils.WidgetInitContext(builder, menuBuilder, x, y, width, height, message);
            consumerCallback(builder.onInit, context, "Error in " + menuName() + "builder for field: onInit.");
        }
    }

    public String menuName() {
        return this.menuBuilder.id.toString();
    }

    public AbstractWidgetBuilder getBuilder() {
        return builder;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int i, int i1, float v) {
        if (builder.renderWidget != null) {
            final ContextUtils.ScreenRenderContextW context = new ContextUtils.ScreenRenderContextW(this, guiGraphics, v, i, i1);
            consumerCallback(builder.renderWidget, context, "Error in " + menuName() + "builder for field: renderWidget.");
        }

    }


    @Override
    protected void updateWidgetNarration(NarrationElementOutput p_169396_) {
        var context = new ContextUtils.NarrationStateContextW(this, p_169396_);
        if (builder.updateWidgetNarration != null) {
            consumerCallback(builder.updateWidgetNarration, context, "Error in " + menuName() + "builder for field: updateWidgetNarration.");
        }

        if (builder.onUpdateWidgetNarration != null) {
            consumerCallback(builder.onUpdateWidgetNarration, context, "Error in " + menuName() + "builder for field: onUpdateWidgetNarration.");
        }
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        var context = new ContextUtils.ScreenRenderContextW(this, pGuiGraphics, pPartialTick, pMouseX, pMouseY);
        if (builder.render != null) {
            consumerCallback(builder.render, context, "Error in " + menuName() + "builder for field: render.");
        } else super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        if (builder.onRender != null) {
            consumerCallback(builder.onRender, context, "Error in " + menuName() + "builder for field: onRender.");
        }
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        if (builder.onClick != null) {
            var context = new ContextUtils.OnClickContext(this, pMouseX, pMouseY);
            consumerCallback(builder.onClick, context, "Error in " + menuName() + " builder for field: onClick.");
        }
        super.onClick(pMouseX, pMouseY);
    }


    @Override
    public void onRelease(double pMouseX, double pMouseY) {
        if (builder.onRelease != null) {
            var context = new ContextUtils.OnClickContext(this, pMouseX, pMouseY);
            consumerCallback(builder.onRelease, context, "Error in " + menuName() + " builder for field: onRelease.");
        }
        super.onRelease(pMouseX, pMouseY);
    }

    @Override
    protected void onDrag(double pMouseX, double pMouseY, double pDragX, double pDragY) {
        if (builder.onDrag != null) {
            var context = new ContextUtils.DragContext(this, pMouseX, pMouseY, pDragX, pDragY);
            consumerCallback(builder.onDrag, context, "Error in " + menuName() + " builder for field: onDrag.");
        }
        super.onDrag(pMouseX, pMouseY, pDragX, pDragY);
    }


    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (builder.mouseClicked != null) {
            try {
                var context = new ContextUtils.MouseClickedContextW(this, pMouseX, pMouseY, pButton);
                var obj = convertObjectToDesired(builder.mouseClicked.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for mouseClicked from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.mouseClicked(pMouseX, pMouseY, pButton));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field mouseClicked: " + menuName() + ".", e);
            }
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }


    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (builder.mouseReleased != null) {
            try {
                var context = new ContextUtils.MouseClickedContextW(this, pMouseX, pMouseY, pButton);
                var obj = convertObjectToDesired(builder.mouseReleased.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for mouseReleased from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.mouseReleased(pMouseX, pMouseY, pButton));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field mouseReleased: " + menuName() + ".", e);
            }
        }
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }


    @Override
    protected boolean isValidClickButton(int pButton) {
        if (builder.isValidClickButton != null) {
            try {
                var context = new ContextUtils.ValidClickButtonContext(this, pButton);
                var obj = convertObjectToDesired(builder.isValidClickButton.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for isValidClickButton from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.isValidClickButton(pButton));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field isValidClickButton: " + menuName() + ".", e);
            }
        }
        return super.isValidClickButton(pButton);
    }


    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (builder.mouseDragged != null) {
            try {
                var context = new ContextUtils.MouseDraggedContextW(this, pMouseX, pMouseY, pButton, pDragX, pDragY);
                var obj = convertObjectToDesired(builder.mouseDragged.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for mouseDragged from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field mouseDragged: " + menuName() + ".", e);
            }
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }


    @Override
    protected boolean clicked(double pMouseX, double pMouseY) {
        if (builder.clicked != null) {
            try {
                var context = new ContextUtils.ClickedContextW(this, pMouseX, pMouseY);
                var obj = convertObjectToDesired(builder.clicked.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for clicked from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.clicked(pMouseX, pMouseY));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field clicked: " + menuName() + ".", e);
            }
        }
        return super.clicked(pMouseX, pMouseY);
    }


    @Nullable
    @Override
    public ComponentPath nextFocusPath(FocusNavigationEvent pEvent) {
        if (builder.nextFocusPath != null) {
            try {
                var context = new ContextUtils.NextFocusPathContextW(this, pEvent);
                var obj = builder.nextFocusPath.apply(context);
                if (obj instanceof ComponentPath path) {
                    return path;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for nextFocusPath from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a ComponentPath. Defaulting to super method: " + super.nextFocusPath(pEvent));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field nextFocusPath: " + menuName() + ".", e);
            }
        }
        return super.nextFocusPath(pEvent);
    }


    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        if (builder.isMouseOver != null) {
            try {
                var context = new ContextUtils.IsMouseOverContextW(this, pMouseX, pMouseY);
                var obj = convertObjectToDesired(builder.isMouseOver.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for isMouseOver from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.isMouseOver(pMouseX, pMouseY));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field isMouseOver: " + menuName() + ".", e);
            }
        }
        return super.isMouseOver(pMouseX, pMouseY);
    }


    @Override
    public void playDownSound(SoundManager pHandler) {
        var context = new ContextUtils.PlayDownSoundContext(this, pHandler);
        if (builder.onPlayDownSound != null) {
            consumerCallback(builder.onPlayDownSound, context, "Error in " + menuName() + " builder for field: onPlayDownSound.");
        }
        if (builder.playDownSound != null) {
            consumerCallback(builder.playDownSound, context, "Error in " + menuName() + " builder for field: playDownSound.");
        } else {
            super.playDownSound(pHandler);
        }
    }


    @Override
    public boolean isHoveredOrFocused() {
        if (builder.isHoveredOrFocused != null) {
            try {
                var context = new ContextUtils.IsHoveredOrFocusedContextW(this);
                var obj = convertObjectToDesired(builder.isHoveredOrFocused.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for isHoveredOrFocused from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.isHoveredOrFocused());
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field isHoveredOrFocused: " + menuName() + ".", e);
            }
        }
        return super.isHoveredOrFocused();
    }


    @Override
    public void setFocused(boolean pFocused) {
        var context = new ContextUtils.SetFocusedContext(this, pFocused);
        if (builder.onSetFocused != null) {
            consumerCallback(builder.onSetFocused, context, "Error in " + menuName() + " builder for field: onSetFocused.");
        }
        if (builder.setFocused != null) {
            consumerCallback(builder.setFocused, context, "Error in " + menuName() + " builder for field: setFocused.");
        } else {
            super.setFocused(pFocused);
        }
    }


    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        var context = new ContextUtils.MouseMovedContext(this, pMouseX, pMouseY);
        if (builder.onMouseMoved != null) {
            consumerCallback(builder.onMouseMoved, context, "Error in " + menuName() + " builder for field: onMouseMoved.");
        }
        if (builder.mouseMoved != null) {
            consumerCallback(builder.mouseMoved, context, "Error in " + menuName() + " builder for field: mouseMoved.");
        } else {
            super.mouseMoved(pMouseX, pMouseY);
        }
    }


    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (builder.mouseScrolled != null) {
            try {
                var context = new ContextUtils.MouseScrolledContextW(this, pMouseX, pMouseY, pDelta);
                var obj = convertObjectToDesired(builder.mouseScrolled.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for mouseScrolled from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.mouseScrolled(pMouseX, pMouseY, pDelta));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field mouseScrolled: " + menuName() + ".", e);
            }
        }
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }


    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (builder.keyPressed != null) {
            try {
                var context = new ContextUtils.KeyPressedContextW(this, pKeyCode, pScanCode, pModifiers);
                var obj = convertObjectToDesired(builder.keyPressed.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for keyPressed from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.keyPressed(pKeyCode, pScanCode, pModifiers));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field keyPressed: " + menuName() + ".", e);
            }
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }


    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if (builder.keyReleased != null) {
            try {
                var context = new ContextUtils.KeyReleasedContextW(this, pKeyCode, pScanCode, pModifiers);
                var obj = convertObjectToDesired(builder.keyReleased.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for keyReleased from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.keyReleased(pKeyCode, pScanCode, pModifiers));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field keyReleased: " + menuName() + ".", e);
            }
        }
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }


    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        if (builder.charTyped != null) {
            try {
                var context = new ContextUtils.CharTypedContextW(this, pCodePoint, pModifiers);
                var obj = convertObjectToDesired(builder.charTyped.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for charTyped from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.charTyped(pCodePoint, pModifiers));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field charTyped: " + menuName() + ".", e);
            }
        }
        return super.charTyped(pCodePoint, pModifiers);
    }


    @Nullable
    @Override
    public ComponentPath getCurrentFocusPath() {
        if (builder.getCurrentFocusPath != null) {
            try {
                var obj = builder.getCurrentFocusPath.apply(this);
                if (obj instanceof ComponentPath path) {
                    return path;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for getCurrentFocusPath from widget builder in menu: " + menuName() + ". Value: " + obj + ". Must be a ComponentPath. Defaulting to super method: " + super.getCurrentFocusPath());
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field getCurrentFocusPath: " + menuName() + ".", e);
            }
        }
        return super.getCurrentFocusPath();
    }


}