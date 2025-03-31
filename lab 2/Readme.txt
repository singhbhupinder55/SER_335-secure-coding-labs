Bhupinder Singh
ASURITE: bsingh55
Course: SER 335 – Lab 1 – Part 1
Date: March 29, 2025

===========================================
Overview:
This submission completes all tasks required for Part I (Host Security) of Lab 1. The Java File Manager (JFM) project has been enhanced to support:
- Authentication using salted passwords (Task H1)
- Role-Based Access Control (RBAC) (Task H2)
- Change password functionality for admins (Task H3)

===========================================
Tasks Implemented:

Task H1 - Authentication (LoginPannel.java):
-------------------------------------------
• Implemented `validateUser()` method to:
  1. Check if the username exists in `userRoleMapping`
  2. Confirm role matches the user's current role
  3. Generate a SipHash of the entered password using user's salt
  4. Compare hash to stored password hash for login success

Task H2 - Role-Based Access Control (MainPanel.java):
-----------------------------------------------------
• Dynamically shows/hides buttons based on privileges loaded from `authorization.json`
• Privileges retrieved via `RolesSingleton.getRoleMapping().getPrivilegesForRole(userRole)`
• Buttons such as View, Edit, Copy, Move, Mkdir, Add User, and Change Password only appear if allowed

Task H3 - Change Password Functionality (Admin only):
-----------------------------------------------------
• Added a new button `"Change Password"` for admin role in `MainPanel.java`
• Handled by `ChangePasswordAction.java` → opens a custom `ChangePasswordPannel.java` dialog
• Admin provides username, new password, and role:
  - Validates fields (Step 4a)
  - Checks if username exists (Step 4b)
  - Validates role match (Step 4c)
  - Updates password and salt in memory and JSON files using `UsersSingleton.updateUserPassword(...)` (Step 5)

===========================================
Files Modified or Added:

• org/jfm/main/LoginPannel.java         → Task H1
• org/jfm/main/MainPanel.java           → Task H2, H3
• org/jfm/po/ChangePasswordPannel.java  → Task H3
• org/jfm/po/ChangePasswordAction.java  → Task H3
• edu/asu/ser335/jfm/UsersSingleton.java→ Task H3 (new method + refactoring)
• authorization.json                    → Added "ChangePassword" privilege to "admin" role

===========================================
Build Instructions:
1. Open terminal and navigate to root directory (`jfm`)
2. Run:  `ant clean`
3. Then: `ant post-clean`
4. Finally: `ant dist` to rebuild the project
5. Run the application via the generated jar in the `jfm-0.9.1` directory

IMPORTANT: No hardcoded paths are used. All paths are relative and files load from the `Resources` directory.

===========================================
Credentials for Testing (from provided JSON files):
• User: adam     | Role: admin     | Pass: 123456
• User: zampa    | Role: developer | Pass: 123456
• User: tim      | Role: user      | Pass: 123456

===========================================

Course: SER 335 – Lab 2  

──────────────────────────────────────────
PART II – NETWORK SECURITY (Sniffing & Spoofing)
──────────────────────────────────────────
Included in `bsingh55_part4.zip`.

✔ Task N1 – Sniffing & Spoofing examples with prevention techniques  
✔ Task N2 – Packet capture using `sniffer.py` and filtering criteria:  
   - ICMP  
   - TCP from 10.9.0.5 to port 23  
   - Packets involving 173.194.208.0/24  
✔ Task N3 – Spoofing ICMP packets from attacker1 and attacker2  
✔ Task N4 – Combined sniff-and-then-spoof implementation using `taskn4.py`

📁 Folder Structure (inside bsingh55_part4.zip):
- `task_n.md` – Answers, analysis, and inline screenshots for Tasks N1–N4  
- `taskn4.py` – Python script for sniff-and-then-spoof logic  
- `n2/`, `n3/`, `n4/` – Clear screenshots of terminal output and tcpdump results for each task  

⚠️ Note:
Wireshark did not capture packets on Mac due to Docker Desktop’s networking limitations on ARM-based machines. As a fallback, `tcpdump` was used successfully to demonstrate spoofed replies and ICMP traffic.


──────────────────────────────────────────
GitHub Repository for Full Project View:
📎 https://github.com/singhbhupinder55/SER_335/tree/main/lab%202
──────────────────────────────────────────
──────────────────────────────────────────
Let me know if any clarification is needed.
Thank you!
──────────────────────────────────────────


