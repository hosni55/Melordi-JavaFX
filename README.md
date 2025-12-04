# Melordi 🎹

A modern JavaFX musical keyboard application that simulates various instruments with MIDI sound generation.

## ✨ Features

- **Interactive Keyboard**: 8-key virtual keyboard mapped to keyboard keys (U, I, O, P, J, K, L, M).
- **Multiple Instruments**: Switch seamlessly between Piano, Guitar, and Organ.
- **Visual Feedback**: Dynamic blue gradient animations on key press.
- **Auto-Play**: Built-in "Für Elise" melody player with speed control.
- **Volume Control**: Real-time MIDI velocity adjustment.
- **Modern UI**: Clean, centered interface with custom styling.

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 25 or later (must include JavaFX).
- Recommended: [Zulu FX JDK](https://www.azul.com/downloads/?package=jdk-javafx).

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/melordi-javafx.git
   cd melordi-javafx
   ```

2. **Compile the project**
   ```bash
   compile.bat
   ```

3. **Run the application**
   ```bash
   run.bat
   ```

## 🎮 Controls

| Key | Note |
|-----|------|
| **U** | Do (C) |
| **I** | Ré (D) |
| **O** | Mi (E) |
| **P** | Fa (F) |
| **J** | Sol (G) |
| **K** | La (A) |
| **L** | Si (B) |
| **M** | Do (C) |

- **Mouse**: Click on keys to play notes.
- **Play/Stop Button**: Toggle the automatic melody.
- **Instrument Selector**: Choose your sound.
- **Volume Slider**: Adjust audio level.

## 📂 Project Structure

```
melordi-javafx/
├── Melordi.java        # Main application entry point & UI layout
├── Clavier.java        # Keyboard component managing keys
├── Touche.java         # Individual key component with visual effects
├── ChangeInstru.java   # Instrument selection panel
├── Son.java            # Volume control component
├── Instru.java         # MIDI audio engine wrapper
├── fur_elise.txt       # Melody sequence data
└── images/             # UI assets (instrument icons)
```

## 🛠️ Built With

- **JavaFX** - GUI Framework
- **javax.sound.midi** - Audio Generation

## 📝 License

This project is open source and available for educational purposes.
