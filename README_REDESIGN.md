# Grand View Hotel Management System — redesigned edition

This is an enhancement of the uploaded Java Swing / NetBeans project. Existing customer, room, employee, reception and checkout screens are retained. The main dashboard has been redesigned with a modern sidebar, summary cards, quick actions and new operational modules.

## New modules
- **Reservations** — create bookings, review the register and update booking status.
- **Billing & invoices** — record invoices, payments and outstanding balances.
- **Housekeeping** — assign room tasks, set priority and track status.
- **Guest services** — log room-service, laundry and maintenance requests.
- **Reports & analytics** — view live counts for rooms, customers, reservations, invoices and open tasks.
- **Dashboard KPIs** — room and guest counts, active bookings and recorded invoice amount.
- **Safer DB configuration** — database connection details can be supplied through environment variables instead of being hard-coded.

## Requirements
- JDK compatible with the NetBeans project settings (currently source/target 22; change to your installed JDK in Project Properties if needed).
- MySQL Server.
- MySQL Connector/J in the project classpath.
- The original project references `rs2xml.jar` and a MySQL connector using a Windows-specific absolute path in `nbproject/project.properties`. Re-add these JARs through NetBeans **Project Properties → Libraries** on your computer, or update the paths to local copies.

## Database setup
1. Back up your current database first.
2. Create/use database `hotelmanagementsystem1` and ensure the original tables (`login`, `room`, `customer`, `employee`, `department`, and any other tables used by the existing screens) are present.
3. Run `database/hotel_upgrade.sql` in MySQL Workbench or the MySQL CLI. It adds tables for reservations, invoices, housekeeping tasks and guest service requests. It does not replace the original tables.
4. Set environment variables before starting the app:
   - `HOTEL_DB_URL=jdbc:mysql://localhost:3306/hotelmanagementsystem1?useSSL=false&serverTimezone=UTC`
   - `HOTEL_DB_USER=your_mysql_user`
   - `HOTEL_DB_PASSWORD=your_mysql_password`
5. Open the folder as an existing NetBeans project and run `hotel.management.system.HotelManagementSystem`.

### Windows PowerShell example
```powershell
$env:HOTEL_DB_URL="jdbc:mysql://localhost:3306/hotelmanagementsystem1?useSSL=false&serverTimezone=UTC"
$env:HOTEL_DB_USER="hotel_app"
$env:HOTEL_DB_PASSWORD="your-password"
```

### Linux example
```bash
export HOTEL_DB_URL='jdbc:mysql://localhost:3306/hotelmanagementsystem1?useSSL=false&serverTimezone=UTC'
export HOTEL_DB_USER='hotel_app'
export HOTEL_DB_PASSWORD='your-password'
```

## Important notes
- The new modules require the schema in `database/hotel_upgrade.sql`. If the schema is not installed, those screens show a database/setup message instead of pretending the records loaded.
- Existing screens in the original project contain legacy SQL and should be reviewed before production use. The new module writes use prepared statements. This is a desktop project, not a multi-user web/PMS deployment.
- Do not use the MySQL `root` account or a weak password for a real hotel installation. Use a dedicated database account with only the required permissions.
- No real reservations, payments or guest records have been fabricated or preloaded.
