# 😊 Amoji

An emoji plugin for [AllayMC](https://github.com/AllayMC/Allay) server that displays emoji particle effects above players' heads!

![img.png](img.png)

## ✨ Features

- 🎭 Display emoji particle effects above players' heads
- 💬 Auto-detect emoji phrases in chat messages
- 📝 Customizable emoji names and trigger phrases
- 🎮 Simple GUI for selecting emojis

## 📦 Installation

1. Download the latest release from [Releases](https://github.com/smartcmd/Amoji/releases)
2. Place the JAR file into your server's `plugins` folder
3Restart the server

## 📋 Commands

| Command  | Aliases       | Description                   | Permission            |
|----------|---------------|-------------------------------|-----------------------|
| `/emoji` | `/emj`, `/ej` | Opens the emoji selection GUI | `amoji.command.emoji` |

## ⚙️ Configuration

### config.yml

```yaml
# Enable/disable automatic emoji display when players chat
auto-emoji: true
```

### emoji.yml

Defines the display names for each emoji:

```yaml
smiley: Smiley
grimacing: Grimacing
grin: Grin
joy: Joy
smile: Smile
# ... and more
```

### emoji-phrases.yml

Defines trigger phrases that will automatically display emojis above players when they chat:

```yaml
joy:
  - "haha"
  - "hhhh"
  - "lmao"
  - "lmfao"
smile:
  - "welcome"
  - "wlc"
heart_eyes:
  - "love"
  - "<3"
# ... and more
```

## 🎨 Available Emojis

| Emoji | ID                           | Description                  |
|-------|------------------------------|------------------------------|
| 😊    | smiley                       | Smiley                       |
| 😬    | grimacing                    | Grimacing                    |
| 😀    | grin                         | Grin                         |
| 😂    | joy                          | Joy                          |
| 😄    | smile                        | Smile                        |
| 😅    | sweat_smile                  | Sweat smile                  |
| 😆    | laughing                     | Laughing                     |
| 😇    | innocent                     | Innocent                     |
| 😉    | wink                         | Wink                         |
| 😊    | blush                        | Blush                        |
| 🙂    | slight_smile                 | Slight smile                 |
| 🙃    | upside_down                  | Upside down                  |
| ☺️    | relaxed                      | Relaxed                      |
| 😋    | yum                          | Yum                          |
| 😌    | relieved                     | Relieved                     |
| 😍    | heart_eyes                   | Heart eyes                   |
| 😘    | kissing_heart                | Kissing heart                |
| 😗    | kissing                      | Kissing                      |
| 😙    | kissing_smiling_eyes         | Kissing smiling eyes         |
| 😚    | kissing_closed_eyes          | Kissing closed eyes          |
| 😜    | stuck_out_tongue_winking_eye | Stuck out tongue winking eye |
| 😝    | stuck_out_tongue_closed_eyes | Stuck out tongue closed eyes |
| 😛    | stuck_out_tongue             | Stuck out tongue             |
| 🤑    | money_mouth                  | Money mouth                  |
| 😎    | sunglasses                   | Sunglasses                   |
| 😏    | smirk                        | Smirk                        |
| 😶    | no_mouth                     | No mouth                     |
| 😐    | neutral_face                 | Neutral face                 |
| 😑    | expressionless               | Expressionless               |
| 😒    | unamused                     | Unamused                     |
| 🙄    | rolling_eyes                 | Rolling eyes                 |
| 😳    | flushed                      | Flushed                      |
| 😞    | disappointed                 | Disappointed                 |
| 😟    | worried                      | Worried                      |
| 😠    | angry                        | Angry                        |
| 😡    | rage                         | Rage                         |
| 😔    | pensive                      | Pensive                      |
| 😕    | confused                     | Confused                     |
| 🙁    | slight_frown                 | Slight frown                 |
| ☹️    | frowning2                    | Frowning 2                   |

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
