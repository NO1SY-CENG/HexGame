# 🔷 HexGame - Java Strategy Simulation

[![Java](https://img.shields.io/badge/Java-11%2B-orange)](https://www.oracle.com/java/)
[![Build](https://img.shields.io/badge/Build-Maven-blue)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

## 📖 Proje Özeti (Executive Summary)

**HexGame**, Nash dengesi teorisi ve graf algoritmaları üzerine kurulu klasik Hex masa oyununun Java tabanlı, nesne yönelimli (OOP) bir simülasyonudur. Proje, **Erciyes Üniversitesi** Bilgisayar Mühendisliği müfredatı kapsamında, karmaşık veri yapıları ve kullanıcı arayüzü (GUI) etkileşimlerini göstermek amacıyla geliştirilmiştir.

Bu uygulama, `n x n` boyutlarında dinamik bir altıgen ızgara üzerinde, iki oyuncunun stratejik yol bulma (pathfinding) yeteneklerini test eder.

## 🚀 Temel Özellikler (Key Features)

* **Dinamik Izgara Mimarisi:** Kullanıcı tanımlı tahta boyutları (5x5, 11x11 vb.) ile ölçeklenebilir oyun alanı.
* **Algoritmik Derinlik:** Oyun sonu durumunun (Win Condition) tespiti için optimize edilmiş **DFS (Depth First Search)** veya **Union-Find** algoritmaları.
* **Kullanıcı Deneyimi (UX):** Sezgisel grafik arayüz, geçerli hamle kontrolü ve görsel geri bildirimler.
* **OOP Prensipleri:** `edu.erciyes.hexgame` paketi altında kapsülleme (encapsulation), kalıtım ve polimorfizm prensiplerine uygun modüler yapı.
* **Save/Load Mekanizması:** (Varsa ekle, yoksa bu maddeyi çıkar) Oyun durumunun serileştirilmesi (Serialization) ile oyun kaydetme özelliği.

## 🛠️ Teknik Altyapı (Tech Stack)

| Bileşen | Teknoloji | Açıklama |
| :--- | :--- | :--- |
| **Dil** | Java (JDK 11+) | Core mantık ve bellek yönetimi |
| **GUI** | Java Swing / AWT | Grafik arayüz bileşenleri |
| **Build Tool** | Maven | Bağımlılık yönetimi ve proje yaşam döngüsü |
| **IDE** | IntelliJ IDEA | Geliştirme ortamı |

## ⚙️ Kurulum ve Çalıştırma (Installation)

Projeyi yerel ortamınızda ayağa kaldırmak için:

1.  **Depoyu Klonlayın:**
    ```bash
    git clone [https://github.com/KULLANICI_ADIN/HexGame.git](https://github.com/KULLANICI_ADIN/HexGame.git)
    ```
2.  **Dizin Değiştirin:**
    ```bash
    cd HexGame
    ```
3.  **Projeyi Derleyin (Maven):**
    ```bash
    mvn clean install
    ```
4.  **Uygulamayı Başlatın:**
    `src/main/java/edu/erciyes/hexgame` dizinindeki `Main.java` sınıfını çalıştırın.

## 📷 Ekran Görüntüleri (Preview)

*(Buraya oyun çalışırken alınmış bir ekran görüntüsü eklenecek. Şimdilik placeholder.)*
> ![Game Screenshot](https://via.placeholder.com/600x400?text=HexGame+Interface+Preview)

## 🤝 Katkıda Bulunma (Contributing)

Bu proje eğitim amaçlıdır, ancak geliştirmelere açıktır. Pull Request (PR) göndermeden önce lütfen bir **Issue** açarak tartışınız.

---
**Geliştirici:** [Senin Adın]
**Kurum:** Erciyes Üniversitesi
