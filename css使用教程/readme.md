`flex-grow` 和 `flex-shrink` 是 CSS **Flexbox** 布局模型中的两个属性，用来控制 **flex item（弹性子项）** 如何在 **flex container（弹性容器）** 中占据可用空间，或者如何收缩。

### 1. `flex-grow` （扩展因子）
`flex-grow` 控制 **弹性子项**在容器中分配额外空间时的扩展比例。它决定了子项在父容器中占据剩余空间的能力。

- **默认值**：`0`
- **作用**：如果父容器中有剩余空间，`flex-grow` 会决定每个子项获得多大比例的剩余空间。每个子项的扩展量与其 `flex-grow` 的值成正比。

#### 语法：
```css
.flex-item {
  flex-grow: <number>;
}
```

- `<number>`：指定该子项应该增长的比例，默认值是 `0`，表示不扩展。如果所有子项的 `flex-grow` 都是 `0`，则它们不会扩展，剩余空间会保持空白。

#### 示例：
```html
<div class="container">
  <div class="item">Item 1</div>
  <div class="item">Item 2</div>
  <div class="item">Item 3</div>
</div>

<style>
.container {
  display: flex;
}

.item {
  flex-grow: 1;
}
</style>
```

在这个例子中，三个 `item` 都有 `flex-grow: 1`，意味着它们都会平分父容器中的剩余空间。

如果 `Item 1` 的 `flex-grow` 为 `2`，而 `Item 2` 和 `Item 3` 为 `1`，那么 `Item 1` 会获得父容器剩余空间的两倍。

### 2. `flex-shrink` （收缩因子）
`flex-shrink` 控制 **弹性子项**在容器空间不足时如何收缩。它决定了子项的 **收缩比例**，即当父容器空间不够时，子项会如何缩小。

- **默认值**：`1`
- **作用**：如果容器的总空间不足以容纳所有子项，`flex-shrink` 控制子项在可用空间不足时收缩的优先级和程度。

#### 语法：
```css
.flex-item {
  flex-shrink: <number>;
}
```

- `<number>`：指定该子项应该收缩的比例，默认值是 `1`。如果所有子项的 `flex-shrink` 都是 `1`，它们会均等地收缩。

#### 示例：
```html
<div class="container">
  <div class="item">Item 1</div>
  <div class="item">Item 2</div>
  <div class="item">Item 3</div>
</div>

<style>
.container {
  display: flex;
  width: 300px; /* 限制容器宽度 */
}

.item {
  flex-shrink: 1;
  width: 200px;
}
</style>
```

在这个例子中，所有 `item` 都有相同的 `flex-shrink: 1`，它们会均等地收缩，以适应容器宽度。比如，如果容器宽度只有 `300px`，但所有子项总宽度为 `600px`，每个子项会按照比例收缩。

如果 `Item 1` 的 `flex-shrink` 为 `2`，而其他子项为 `1`，那么 `Item 1` 会收缩两倍于其他子项。

### `flex-grow` 与 `flex-shrink` 示例对比

#### 例子 1：使用 `flex-grow` 实现分配剩余空间

```html
<div class="container">
  <div class="item">Item 1</div>
  <div class="item">Item 2</div>
  <div class="item">Item 3</div>
</div>

<style>
.container {
  display: flex;
  width: 600px; /* 容器宽度 */
}

.item {
  flex-grow: 1; /* 所有子项将平均分配剩余空间 */
  width: 100px;
}
</style>
```

在这个例子中，三个 `item` 的总宽度是 `300px`（每个 `item` 100px），容器的宽度是 `600px`，所以每个子项会均等地扩展，所有子项将平分剩余的 `300px` 空间。

#### 例子 2：使用 `flex-shrink` 实现收缩

```html
<div class="container">
  <div class="item">Item 1</div>
  <div class="item">Item 2</div>
  <div class="item">Item 3</div>
</div>

<style>
.container {
  display: flex;
  width: 500px; /* 容器宽度 */
}

.item {
  flex-shrink: 1; /* 所有子项可以收缩 */
  width: 250px; /* 子项宽度大于容器宽度 */
}
</style>
```

在这个例子中，子项的总宽度是 `750px`，而容器宽度只有 `500px`，因此子项会收缩。由于 `flex-shrink` 的默认值是 `1`，所有子项将会以相同的比例进行收缩。如果设置不同的 `flex-shrink` 值，就会有不同的收缩优先级。

### `flex-grow`、`flex-shrink` 和 `flex-basis` 的综合用法

`flex-grow` 和 `flex-shrink` 通常与 `flex-basis` 一起使用，来指定子项的初始大小和如何扩展或收缩。

```css
.flex-item {
  flex: 1 1 100px;  /* flex-grow: 1, flex-shrink: 1, flex-basis: 100px */
}
```

- `flex-grow: 1`：在有剩余空间时，子项可以增长。
- `flex-shrink: 1`：在空间不足时，子项可以收缩。
- `flex-basis: 100px`：设置每个子项的初始宽度。

### 总结

- **`flex-grow`**：控制子项如何在剩余空间中扩展。
    - 默认值是 `0`，表示不扩展。
    - 如果多个子项的 `flex-grow` 值相同，它们会均等扩展。

- **`flex-shrink`**：控制子项如何在空间不足时收缩。
    - 默认值是 `1`，表示所有子项都可以收缩。
    - 如果某个子项的 `flex-shrink` 值较大，它会比其他子项收缩得更多。

这两个属性帮助你精确控制 Flexbox 容器中子项如何响应可用空间的变化。
