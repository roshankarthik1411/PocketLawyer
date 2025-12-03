# 🚨 **PocketLawyer — Your AI-Powered Legal First Aid Kit**

### *A real-time legal assistant for emergencies, rights awareness & effortless FIR/evidence management*

---

## 🧭 **Table of Contents**

* [About the Project](#about-the-project)
* [Key Features](#key-features)
* [Core Screens](#core-screens)
* [Tech Stack](#tech-stack)
* [Architecture](#architecture)
* [App Flow Diagram](#app-flow-diagram)
* [Installation](#installation)
* [Project Structure](#project-structure)
* [Screenshots](#screenshots)
* [Challenges Faced](#challenges-faced)
* [Future Scope](#future-scope)
* [Team](#team)

---

# 🧩 **About the Project**

**PocketLawyer** is an AI-assisted legal helper designed to provide instant legal awareness, generate FIR drafts, manage digital evidence, and educate citizens about their rights — **all offline and accessible anytime**.

Our goal is simple:
👉 **Put the law in everyone’s pocket.**

From roadside police checks to workplace harassment, PocketLawyer gives people:

* The **correct information**
* In **real time**
* In **simple language**
* With **AI-generated PDF summaries**

---

# 🚀 **Key Features**

### **🛡️ Know Your Rights**

* Smart search across all legal categories
* 7 categories covering 40+ rights
* Short overview + long detailed PDF explanation
* Offline first
* Downloadable multi-page PDF per right

---

### **📝 FIR / Complaint Generator**

* Easy input fields
* AI-generated FIR format
* PDF export
* Share directly with police or legal advisor

*(Optional or future, depending on your build)*

---

### **📁 Evidence Manager**

* Save text notes
* Add photo/video evidence
* Easy delete / manage
* Secure local storage

---

### **🚨 Emergency Action Buttons** *(optional feature)*

* “What to do if stopped by police?”
* “Emergency harassment help”
* “Call police 100” shortcut
* “Auto evidence capture”

---

### **📄 PDF Generator**

* Multi-page PDF generator
* Automatic text wrapping
* Bold titles, clean layout
* Works even offline

---

# 📱 **Core Screens**

| Screen               | Description                      |
| -------------------- | -------------------------------- |
| **Home Screen**      | Entry to all major tools         |
| **Know Your Rights** | Category-based legal rights list |
| **Search System**    | Smart keyword + semantic search  |
| **FIR Generator**    | Structured form → Draft FIR      |
| **Evidence Manager** | Save/delete evidence             |
| **PDF Viewer**       | Open generated PDFs              |

---

# 🛠️ **Tech Stack**

### **Frontend**

* **Jetpack Compose (Material 3)**
* Kotlin
* LiveData / StateFlow
* LazyColumn, Cards, Composables

### **Backend**

* Local storage (SharedPreferences / Files)
* Mult-page PDF generator
* Data models (Kotlin data classes)

### **Build Tools**

* Android Studio
* Gradle
* GitHub

---

# 🧱 **Architecture**

A clean, modular structure with reusable components:

```
PocketLawyer/
│
├── app/
│   ├── data/
│   │   ├── rights/
│   │   │   ├── RightsData.kt
│   │   │   ├── RightItem.kt
│   │   │
│   │   └── evidence/
│   │
│   ├── ui/
│   │   ├── screens/
│   │   │   ├── KnowYourRightsScreen.kt
│   │   │   ├── EvidenceManagerScreen.kt
│   │   │   └── FIRScreen.kt
│   │   │
│   │   └── components/
│   │
│   ├── util/
│   │    └── PdfGenerator.kt
│   │
│   ├── navigation/
│   │    └── AppNavHost.kt
│   │
│   └── MainActivity.kt
│
└── README.md
```

---

# 🔁 **App Flow Diagram**

```
                ┌───────────────┐
                │   Home Screen  │
                └───────┬───────┘
                        │
     ┌──────────────────┼─────────────────┐
     │                  │                 │
     ▼                  ▼                 ▼
Know Your Rights   Evidence Manager    FIR Generator
     │                  │                 │
     ▼                  ▼                 ▼
 Rights Detail → PDF   Add/Delete     Generate PDF
     │
     ▼
Multi-page PDF Viewer
```

---

# ⚙️ **Installation**

### **1️⃣ Clone the repo**

```
git clone https://github.com/roshankarthik1411/PocketLawyer.git
```

### **2️⃣ Open in Android Studio**

* Open Android Studio
* Select **Open Existing Project**
* Choose the **PocketLawyer** folder

### **3️⃣ Build & Run**

* Connect Android device
* Press **Run ▶️**

---

# 🖼️ **Screenshots**

![Know Your Rights – Police Interaction](https://drive.google.com/file/d/1n87mYeH2bXcWevn-qiY0yC8jkOsDL0qZ/view?usp=drivesdk)
![Evidence Manager – Saved Evidence](YOUR_IMAGE_URL_HERE)
![Know Your Rights – Sensitive Cases](YOUR_IMAGE_URL_HERE)
![Know Your Rights – Police & Arrest](YOUR_IMAGE_URL_HERE)
![Pocket Lawyer – Home Screen](YOUR_IMAGE_URL_HERE)

---

# 🧗‍♂️ **Challenges Faced**

### 🔹 Offline PDF generation

Handled with custom text wrapping + auto page creation.

### 🔹 Smart search across categories

Implemented hybrid search:

* Title match
* Body match
* Category match
* Token-based match (“police stop” → “Stopped by Police”)

### 🔹 GitHub conflicts

Solved issues with branch syncing and missing `.git` folder.

### 🔹 Legal content accuracy

Extended RightsData into summaries + fullText for legal validity.

---

# 🌱 **Future Scope**

* AI Chatbot using Gemini / GPT API
* Voice-to-FIR Generator
* One-touch SOS with auto evidence
* Multilingual support (Hindi, Telugu)
* Cloud backup of evidence
* Lawyer-connect service

---

# 👥 **Team**

**Roshan Karthik (Lead Developer)**

* Jetpack Compose UI
* RightsData architecture
* PDF Generator
* GitHub deployment

**Anvesh (Android Dev)**

* UI integration
* Navigation
* Evidence Manager

---

# 🎉 **Thanks for checking out PocketLawyer!**

