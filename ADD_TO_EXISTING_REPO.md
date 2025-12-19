# 📤 Add Project to Existing GitHub Repository

## 🎯 Steps to Push to Existing Repository

### **Step 1: Open Terminal in Project Folder**

```powershell
cd d:\projects\CAPSTONE_PROJECT
```

### **Step 2: Initialize Git (if not already done)**

```powershell
git init
```

### **Step 3: Add All Files**

```powershell
git add .
```

### **Step 4: Commit Files**

```powershell
git commit -m "Add capstone project files"
```

### **Step 5: Connect to Existing Repository**

```powershell
# Replace YOUR_USERNAME and REPOSITORY_NAME with your actual values
git remote add origin https://github.com/YOUR_USERNAME/REPOSITORY_NAME.git
```

**Example:**
```powershell
git remote add origin https://github.com/johnsmith/my-capstone-project.git
```

### **Step 6: Check if Remote Already Exists (if you get error)**

If you get error "remote origin already exists", use this instead:

```powershell
# Remove existing remote
git remote remove origin

# Add your repository
git remote add origin https://github.com/YOUR_USERNAME/REPOSITORY_NAME.git
```

### **Step 7: Pull Existing Content (if repository has files)**

```powershell
# Pull existing files from repository
git pull origin main --allow-unrelated-histories
```

**OR if repository uses `master` branch:**

```powershell
git pull origin master --allow-unrelated-histories
```

### **Step 8: Push to GitHub**

**If repository uses `main` branch:**
```powershell
git branch -M main
git push -u origin main
```

**If repository uses `master` branch:**
```powershell
git branch -M master
git push -u origin master
```

---

## 🔍 How to Find Your Repository URL

1. Go to your GitHub repository page
2. Click the green **"Code"** button
3. Copy the HTTPS URL (looks like: `https://github.com/USERNAME/REPO_NAME.git`)

---

## ⚠️ Important Notes

### **If Repository Already Has Files:**

**Option A: Merge (Recommended)**
```powershell
git pull origin main --allow-unrelated-histories
# Resolve any conflicts if they occur
git push origin main
```

**Option B: Force Push (⚠️ Overwrites existing files)**
```powershell
git push -u origin main --force
```
**⚠️ Warning:** This will DELETE all existing files in the repository!

### **If Repository is Empty:**

Just push directly:
```powershell
git push -u origin main
```

---

## 📋 Complete Command Sequence

```powershell
# Navigate to project
cd d:\projects\CAPSTONE_PROJECT

# Initialize Git
git init

# Add all files
git add .

# Commit
git commit -m "Add capstone project - Travel Tours System"

# Add remote (replace with your repository URL)
git remote add origin https://github.com/YOUR_USERNAME/REPOSITORY_NAME.git

# Pull existing content (if repository has files)
git pull origin main --allow-unrelated-histories

# Push to GitHub
git branch -M main
git push -u origin main
```

---

## 🆘 Troubleshooting

### **Error: "remote origin already exists"**
```powershell
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/REPOSITORY_NAME.git
```

### **Error: "failed to push some refs"**
```powershell
# Pull first, then push
git pull origin main --allow-unrelated-histories
git push origin main
```

### **Error: "authentication failed"**
- Use GitHub Personal Access Token instead of password
- Or use GitHub Desktop app

### **Error: "branch 'main' does not exist"**
```powershell
# Check what branch exists
git branch -a

# Use the correct branch name (might be 'master')
git push -u origin master
```

---

## ✅ Quick Checklist

- [ ] Navigated to project folder
- [ ] Initialized Git (`git init`)
- [ ] Added files (`git add .`)
- [ ] Committed files (`git commit`)
- [ ] Added remote repository URL
- [ ] Pulled existing content (if needed)
- [ ] Pushed to GitHub

---

**That's it! Your project will be added to your existing repository! 🚀**
