package net.liopyu.menujs.client.container;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.Music;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static net.liopyu.menujs.util.MenuJSHelperClass.consumerCallback;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@OnlyIn(Dist.CLIENT)
public class AbstractContainerScreenJS<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> implements ContainerListener {
    private final AbstractMenuContainerBuilder<T> builder;
    private final Inventory playerInventory;

    public AbstractContainerScreenJS(AbstractMenuContainerBuilder<T> builder, T pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.builder = builder;
        this.playerInventory = pPlayerInventory;
    }



    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    protected void init() {
        super.init();
        var context = new ContextUtils.ScreenBuilderContext<>(this,builder,getMenu(),getPlayerInventory(),getTitle());
        builder.onScreenInit.accept(context);
        this.menu.addSlotListener(this);
    }

    public void removed() {
        super.removed();
        this.menu.removeSlotListener(this);
    }
    public String menuName(){
        return this.builder.id.toString();
    }
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        if (builder.renderBg != null) {
            final ContextUtils.ScreenRenderContext<T> context = new ContextUtils.ScreenRenderContext<>(this,guiGraphics,v,i,i1);
            consumerCallback(builder.renderBg, context, "[EntityJS]: Error in " + menuName() + "builder for field: renderBg.");
        }
    }

    @Override
    public void slotChanged(AbstractContainerMenu abstractContainerMenu, int i, ItemStack itemStack) {
        if (builder.onScreenSlotChanged != null) {
            final ContextUtils.MenuItemContext<T> context = new ContextUtils.MenuItemContext<>(this,getMenu(),i,itemStack);
            consumerCallback(builder.onScreenSlotChanged, context, "[EntityJS]: Error in " + menuName() + "builder for field: onScreenSlotChanged.");
        }
    }

    @Override
    public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int i1) {
        if (builder.onDataChanged != null) {
            final ContextUtils.DataChangedContext<T> context = new ContextUtils.DataChangedContext<>(getMenu(),this,i,i1);
            consumerCallback(builder.onDataChanged, context, "[EntityJS]: Error in " + menuName() + "builder for field: onDataChanged.");
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderTooltip(GuiGraphics pGuiGraphics, int pX, int pY) {
        super.renderTooltip(pGuiGraphics, pX, pY);
    }

    @Override
    protected List<Component> getTooltipFromContainerItem(ItemStack pStack) {
        return super.getTooltipFromContainerItem(pStack);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        super.renderLabels(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    protected boolean hasClickedOutside(double pMouseX, double pMouseY, int pGuiLeft, int pGuiTop, int pMouseButton) {
        return super.hasClickedOutside(pMouseX, pMouseY, pGuiLeft, pGuiTop, pMouseButton);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    @Override
    public void clearDraggingState() {
        super.clearDraggingState();
    }

    @Override
    protected boolean isHovering(int pX, int pY, int pWidth, int pHeight, double pMouseX, double pMouseY) {
        return super.isHovering(pX, pY, pWidth, pHeight, pMouseX, pMouseY);
    }

    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
        super.slotClicked(pSlot, pSlotId, pMouseButton, pType);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    protected boolean checkHotbarKeyPressed(int pKeyCode, int pScanCode) {
        return super.checkHotbarKeyPressed(pKeyCode, pScanCode);
    }

    @Override
    public boolean isPauseScreen() {
        return super.isPauseScreen();
    }

    @Override
    protected void containerTick() {
        super.containerTick();
    }

    @Override
    public T getMenu() {
        return super.getMenu();
    }

    @Override
    public @Nullable Slot getSlotUnderMouse() {
        return super.getSlotUnderMouse();
    }

    @Override
    public int getGuiLeft() {
        return super.getGuiLeft();
    }

    @Override
    public int getGuiTop() {
        return super.getGuiTop();
    }

    @Override
    public int getXSize() {
        return super.getXSize();
    }

    @Override
    public int getYSize() {
        return super.getYSize();
    }

    @Override
    public int getSlotColor(int index) {
        return super.getSlotColor(index);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public Component getTitle() {
        return super.getTitle();
    }

    @Override
    public Component getNarrationMessage() {
        return super.getNarrationMessage();
    }

    @Override
    protected void setInitialFocus(GuiEventListener pListener) {
        super.setInitialFocus(pListener);
    }

    @Override
    protected void changeFocus(ComponentPath pPath) {
        super.changeFocus(pPath);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return super.shouldCloseOnEsc();
    }

    @Override
    protected <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget(T pWidget) {
        return super.addRenderableWidget(pWidget);
    }

    @Override
    protected <T extends Renderable> T addRenderableOnly(T pRenderable) {
        return super.addRenderableOnly(pRenderable);
    }

    @Override
    protected <T extends GuiEventListener & NarratableEntry> T addWidget(T pListener) {
        return super.addWidget(pListener);
    }

    @Override
    protected void removeWidget(GuiEventListener pListener) {
        super.removeWidget(pListener);
    }

    @Override
    protected void clearWidgets() {
        super.clearWidgets();
    }

    @Override
    protected void insertText(String pText, boolean pOverwrite) {
        super.insertText(pText, pOverwrite);
    }

    @Override
    public boolean handleComponentClicked(@Nullable Style pStyle) {
        return super.handleComponentClicked(pStyle);
    }

    @Override
    protected void rebuildWidgets() {
        super.rebuildWidgets();
    }

    @Override
    public List<? extends GuiEventListener> children() {
        return super.children();
    }

    @Override
    public void added() {
        super.added();
    }

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        super.renderBackground(pGuiGraphics);
    }

    @Override
    public void renderDirtBackground(GuiGraphics pGuiGraphics) {
        super.renderDirtBackground(pGuiGraphics);
    }

    @Override
    protected void repositionElements() {
        super.repositionElements();
    }

    @Override
    public void resize(Minecraft pMinecraft, int pWidth, int pHeight) {
        super.resize(pMinecraft, pWidth, pHeight);
    }

    @Override
    protected boolean isValidCharacterForName(String pText, char pCharTyped, int pCursorPos) {
        return super.isValidCharacterForName(pText, pCharTyped, pCursorPos);
    }

    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        return super.isMouseOver(pMouseX, pMouseY);
    }

    @Override
    public void onFilesDrop(List<Path> pPacks) {
        super.onFilesDrop(pPacks);
    }

    @Override
    public Minecraft getMinecraft() {
        return super.getMinecraft();
    }

    @Override
    public void afterMouseMove() {
        super.afterMouseMove();
    }

    @Override
    public void afterMouseAction() {
        super.afterMouseAction();
    }

    @Override
    public void afterKeyboardAction() {
        super.afterKeyboardAction();
    }

    @Override
    public void handleDelayedNarration() {
        super.handleDelayedNarration();
    }

    @Override
    public void triggerImmediateNarration(boolean pOnlyNarrateNew) {
        super.triggerImmediateNarration(pOnlyNarrateNew);
    }

    @Override
    protected boolean shouldNarrateNavigation() {
        return super.shouldNarrateNavigation();
    }

    @Override
    protected void updateNarrationState(NarrationElementOutput p_169396_) {
        super.updateNarrationState(p_169396_);
    }

    @Override
    protected void updateNarratedWidget(NarrationElementOutput pNarrationElementOutput) {
        super.updateNarratedWidget(pNarrationElementOutput);
    }

    @Override
    public void narrationEnabled() {
        super.narrationEnabled();
    }

    @Override
    public void setTooltipForNextRenderPass(List<FormattedCharSequence> pTooltip) {
        super.setTooltipForNextRenderPass(pTooltip);
    }

    @Override
    public void setTooltipForNextRenderPass(List<FormattedCharSequence> pTooltip, ClientTooltipPositioner pPositioner, boolean pOverride) {
        super.setTooltipForNextRenderPass(pTooltip, pPositioner, pOverride);
    }

    @Override
    protected void setTooltipForNextRenderPass(Component pTooltip) {
        super.setTooltipForNextRenderPass(pTooltip);
    }

    @Override
    public void setTooltipForNextRenderPass(Tooltip pTooltip, ClientTooltipPositioner pPositioner, boolean pOverride) {
        super.setTooltipForNextRenderPass(pTooltip, pPositioner, pOverride);
    }

    @Override
    public ScreenRectangle getRectangle() {
        return super.getRectangle();
    }

    @Nullable
    @Override
    public Music getBackgroundMusic() {
        return super.getBackgroundMusic();
    }

    @Nullable
    @Override
    public GuiEventListener getFocused() {
        return super.getFocused();
    }

    @Override
    public void setFocused(@Nullable GuiEventListener pListener) {
        super.setFocused(pListener);
    }

    @Override
    public Optional<GuiEventListener> getChildAt(double pMouseX, double pMouseY) {
        return super.getChildAt(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public void setFocused(boolean pFocused) {
        super.setFocused(pFocused);
    }

    @Override
    public boolean isFocused() {
        return super.isFocused();
    }

    @Nullable
    @Override
    public ComponentPath getCurrentFocusPath() {
        return super.getCurrentFocusPath();
    }

    @Override
    public void magicalSpecialHackyFocus(@Nullable GuiEventListener pEventListener) {
        super.magicalSpecialHackyFocus(pEventListener);
    }

    @Nullable
    @Override
    public ComponentPath nextFocusPath(FocusNavigationEvent pEvent) {
        return super.nextFocusPath(pEvent);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public int getTabOrderGroup() {
        return super.getTabOrderGroup();
    }
}
