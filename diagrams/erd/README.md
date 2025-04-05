# E-commerce System ERD Diagrams

This directory contains Entity Relationship Diagrams (ERD) for the e-commerce system, split into logical modules for better understanding.

## Diagram Structure

1. `00-complete-system.mmd`: Complete system overview showing all major entities and their relationships
2. `01-user-authentication.mmd`: User management and authentication system
3. `02-product-inventory.mmd`: Product catalog and inventory management
4. `03-order-management.mmd`: Order processing and management

## Key Relationships

### User & Authentication
- Users can have multiple addresses, verification tokens, and roles
- Each user can be associated with multiple orders
- Authentication system includes email verification and password reset capabilities

### Product & Inventory
- Products can have multiple variants (different sizes, colors, etc.)
- Each product has exactly one inventory record
- Products are referenced in order quantities

### Order Management
- Orders are associated with one user and one shipping address
- Orders contain multiple order quantities
- Order quantities link products to orders with specific quantities

## Notation Guide

- `PK`: Primary Key
- `FK`: Foreign Key
- `UK`: Unique Key
- Relationship symbols:
  - `||--||`: One-to-one
  - `||--o{`: One-to-many
  - `}o--o{`: Many-to-many

## View Instructions

These diagrams are in Mermaid format (.mmd files). To view them:
1. Use a Mermaid-compatible viewer or editor
2. View directly in GitHub (which supports Mermaid rendering)
3. Use the Mermaid Live Editor (https://mermaid.live)
