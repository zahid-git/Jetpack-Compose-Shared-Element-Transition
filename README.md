# Jetpack Compose Shared Element Transition

A sample/demo project showcasing **Shared Element Transitions** in **Jetpack Compose** — a modern way to animate UI elements smoothly between screens and states. 🚀

This project demonstrates how to use the new **Shared Element Transition APIs** in Jetpack Compose to create visually rich and seamless transitions between composables (e.g., image, text, card) during navigation or state changes.

---

## 📌 Features

✅ Shared element transitions between Composables  
✅ Examples with and without Navigation  
✅ Integration with `AnimatedContent`, `AnimatedVisibility`  
✅ Multiple transition patterns (image, text, FAB, sheet, etc.)  
✅ Kotlin & Jetpack Compose implementation

## 🧠 What are Shared Element Transitions?

Shared Element Transitions create smooth animations for UI elements that are **shared between screens or states**, maintaining visual continuity for the user. For example, an image or title can smoothly animate from a list screen into a detail screen.

Jetpack Compose provides APIs such as:

- `SharedTransitionLayout` – Root scope that hosts shared elements.
- `Modifier.sharedElement()` – Marks a composable as a shared element.
- `Modifier.sharedBounds()` – Shares bounds between layouts for container transforms.

---

## 🚀 Getting Started

### Requirements

Make sure your project uses a **Jetpack Compose UI + Animation version** that supports Shared Element APIs (1.10+ or newer).

### Setup

1. Clone the repository
```bash
git clone https://github.com/zahid‑git/Jetpack‑Compose‑Shared‑Element‑Transition.git
```

2. Open in Android Studio
3. Build and run the app
4. The app shows multiple shared element transition samples.

### How It Works (in Compose)

To coordinate a shared element:

1. **Wrap your navigation content in a `SharedTransitionLayout`**
    - This provides a `SharedTransitionScope` available to all child composables.
    - Ensures both source and destination screens are under a shared animation scope. 

2. **Assign unique keys** with `rememberSharedContentState(key)`
    - Keys ensure Compose can match elements on both screens.
    - It’s critical that the same key appears on both source and destination.

3. **Use the animation modifier**
    - `Modifier.sharedElement()` → for actual element transitions
    - `Modifier.sharedBounds()` → for container transforms (card → detail)

Example:

```kotlin
AsyncImage(
    painter = product.imageUrl,
    contentDescription = null,
    modifier = Modifier
        .sharedElement(
            rememberSharedContentState(key = "ProductImage-$productId"),
            animatedVisibilityScope = animationScope
        )
        .size(120.dp)
)
```
---

## 📚 Resources

Android official documentation on Shared Element Transitions in Compose — see Compose animation shared elements. Android Developer blog for the latest Compose animation APIs.


## 🤝 Contributing

Contributions are welcome! Feel free to:
1. Report bugs
2. Suggest new samples
3. Open pull requests


## 📄 License

This project is open‑source. Check the LICENSE file for details.

## 📖 Related Reading

This project was inspired by my Medium article that breaks down shared transitions with visuals and implementation tips — perfect if you want to learn the why as well as the how.

https://medium.com/@me.zahidul/jetpack‑compose‑shared‑element‑transitions‑create‑smooth‑android‑animations‑0088ca05c987