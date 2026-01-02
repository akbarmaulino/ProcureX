# ProcureX – Katalon Automation Testing

Repository ini berisi automation testing untuk aplikasi **ProcureX** menggunakan **Katalon Studio (Free Version)**.

Branch `main` merupakan **branch stabil** yang sudah diverifikasi dan digunakan sebagai baseline automation oleh tim.


## 🛠 Tools & Technology
- Katalon Studio (Free)
- Groovy
- Git & GitHub



## 🌳 Branching Strategy

| Branch | Deskripsi |
|------|----------|
| `main` | Branch stabil (baseline automation) |
| `feature/*` | Branch untuk pengembangan test case / automation baru |

### Aturan Branch:
- ❌ Dilarang commit langsung ke `main`
- ✅ Semua perubahan dilakukan di `feature/*`
- ✅ Merge ke `main` hanya dilakukan oleh Automation Lead

Contoh penamaan branch:
- feature/login-test
- feature/register-validation
- feature/checkout-automation

## 📁 Struktur Project

ProcureX/
├─ Test Cases/ 
├─ Test Suites/ 
├─ Object Repository/ 
├─ Keywords/ 
├─ Data Files/ 
├─ Profiles/ 
├─ Reports/ 
├─ output/ 
└─ README.md


## ▶️ Cara Menggunakan Project

### 1. Clone Repository

- git clone https://github.com/akbarmaulino/ProcureX.git

### 2. Buka di Katalon Studio

- Open Katalon Studio

- Pilih File → Open Project

- Arahkan ke folder ProcureX

### 3. Menjalankan Automation

- Pilih Test Suite

- Pilih Profile (default / staging / uat)

- Klik Run

## 🔧 Environment & Configuration

- URL, credential, dan environment variable disimpan di Profiles

- Test data disimpan di Data Files

- ❌ Dilarang hardcode data sensitif di Test Case atau Keyword

## 🧾 Git Rules

### Commit Message

Gunakan format berikut:

- [ADD] Add login positive test
- [FIX] Update submit button locator
- [UPDATE] Refactor common keywords

