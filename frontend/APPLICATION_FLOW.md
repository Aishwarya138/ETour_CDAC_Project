# ETour Application Flow Documentation

## Overview
ETour is a React-based tour booking application built with Vite. It allows users to browse tours, view details, and make bookings through an intuitive web interface.

---

## 1. Application Architecture

### Technology Stack
- **Frontend Framework**: React 19.2.0
- **Routing**: React Router DOM 7.12.0
- **Build Tool**: Vite 7.2.4
- **Language**: JavaScript (JSX)

### Entry Point Flow
```
index.html (root div)
    ↓
main.jsx (React entry point)
    ↓
App.jsx (routing setup)
    ↓
Individual Pages (based on URL route)
```

---

## 2. URL Routing Map

| Route | Component | Purpose |
|-------|-----------|---------|
| `/` | ShowcasePage | Landing/showcase page |
| `/home`, `/tours` | HomePage | Main tour listing page |
| `/tours/:sectorId` | SubSectorPage | Filter tours by sector/category |
| `/tours/:sectorId/:subSectorId` | ProductPage | Display specific tours within subcategory |
| `/tour/:tourId` | TourDetailPage | Detailed view of a single tour |
| `/booking/:tourId` | BookingPage | Booking form and checkout |
| `/search` | SearchPage | Tour search results |
| `/about` | AboutPage | About company |
| `/terms` | TermsPage | Terms & conditions |
| `/contact`, `/feedback` | FeedbackPage | Contact/feedback form |
| `/gallery` | GalleryPage | Photo gallery |
| `/videos` | VideosPage | Video gallery |
| `/download` | DownloadPage | Downloads |
| `/login` | LoginPage | User login |
| `/register` | RegisterPage | User registration |

---

## 3. Component Structure

### Layout Components (`/components/layout`)
```
PageLayout (wrapper component)
├── Header (navigation, branding)
├── Sidebar (filters, categories)
├── Main Content Area (page-specific content)
└── Footer (links, info)

Breadcrumb (navigation trail)
```

### Common Components (`/components/common`)
- **TourCard**: Display individual tour cards with image, rating, price
- **CategoryCard**: Display tour categories
- **Pagination**: Handle page navigation
- **AdBanner**: Display promotional ads
- **LanguageSelector**: Multi-language support

### Form Components (`/components/forms`)
- **SearchForm**: Search/filter tours
- **PassengerForm**: Enter passenger details during booking
- **CustomerForm**: Enter customer information

### Page Components (`/pages`)
- Landing & Navigation: ShowcasePage, HomePage
- Browse & Filter: SubSectorPage, ProductPage, SearchPage
- Details & Booking: TourDetailPage, BookingPage
- Authentication: LoginPage, RegisterPage
- Static: AboutPage, TermsPage, GalleryPage, VideosPage, FeedbackPage, DownloadPage

---

## 4. Data Flow Architecture

### Request Flow
```
User Interface (Pages/Forms)
    ↓
Form Component collects input
    ↓
api.js (services/api.js) - HTTP requests
    ↓
Backend Server (API endpoints)
    ↓
Database
```

### Response Flow
```
Backend Response
    ↓
api.js processes data
    ↓
Page Component receives data
    ↓
UI Renders results (TourCard, Lists, Details)
```

---

## 5. User Journey Example

### Scenario: User Books a Tour

**Step 1: Landing**
- User visits `/` → ShowcasePage
- Sees featured tours, categories, promotional content

**Step 2: Browse**
- Clicks category → `/tours/:sectorId` (SubSectorPage)
- Filters by sector (e.g., "Mountain Tours")

**Step 3: Filter**
- Further filters → `/tours/:sectorId/:subSectorId` (ProductPage)
- Views list of specific tours (e.g., "Hiking Adventures")

**Step 4: View Details**
- Clicks tour card → `/tour/:tourId` (TourDetailPage)
- Views full description, itinerary, pricing, reviews

**Step 5: Booking**
- Clicks "Book Now" → `/booking/:tourId` (BookingPage)
- Enters passenger details via PassengerForm
- Confirms booking

**Step 6: Confirmation**
- Backend processes booking
- User receives confirmation/receipt

---

## 6. Key Features

### Search & Browse
- **HomePage**: Multi-category tour browsing
- **SearchPage**: Advanced tour search
- **SubSectorPage**: Category-based filtering
- **ProductPage**: Subcategory filtering

### Tour Interaction
- **TourDetailPage**: Complete tour information
- **Pagination**: Navigate through tour lists
- **TourCard**: Quick view with ratings and prices

### User Actions
- **SearchForm**: Find specific tours
- **PassengerForm**: Enter booking details
- **CustomerForm**: Capture customer information
- **Pagination**: Navigate results

### Support Pages
- **FeedbackPage**: User feedback/contact
- **GalleryPage**: Visual portfolio
- **VideosPage**: Promotional videos
- **AboutPage**: Company information
- **TermsPage**: Legal information

---

## 7. Styling Architecture

### CSS Files (`/styles`)
| File | Purpose |
|------|---------|
| `variables.css` | CSS variables (colors, sizes, fonts) |
| `index.css` | Global styles |
| `layout.css` | Layout component styles |
| `components.css` | Component-specific styles |
| `pages.css` | Page-specific styles |

### Component Styling
- **App.css**: Application-level styles
- **index.css**: Root HTML element styles

---

## 8. API Service Layer

**File**: `src/services/api.js`

Responsibilities:
- HTTP requests to backend
- Data transformation
- Error handling
- Authentication (if needed)

**Used by**: All page components and forms

---

## 9. State Management

- **React Component State**: Local state within components
- **React Router**: URL-based state (page navigation)
- **Props**: Parent-to-child data passing

*Note: No global state management (Redux/Context) visible - likely using local component state*

---

## 10. Module Exports

### Index Files (for clean imports)
- `/pages/index.js`: Exports all page components
- `/components/layout/index.js`: Exports layout components
- `/components/common/index.js`: Exports common components
- `/components/forms/index.js`: Exports form components

**Usage Example**:
```javascript
import { HomePage, TourDetailPage } from './pages';
```

---

## 11. Build & Development

### Development
```bash
npm run dev    # Start Vite dev server
```

### Production
```bash
npm run build  # Build for production
npm run preview # Preview production build
```

### Code Quality
```bash
npm run lint   # Run ESLint
```

---

## 12. File Structure Summary

```
frontend/
├── index.html              # HTML entry point
├── vite.config.js          # Vite configuration
├── eslint.config.js        # Linting rules
├── package.json            # Dependencies
├── src/
│   ├── main.jsx            # React app initialization
│   ├── App.jsx             # Router setup
│   ├── App.css             # App styles
│   ├── pages/              # Page components (13 pages)
│   ├── components/
│   │   ├── layout/         # Layout wrappers
│   │   ├── common/         # Reusable components
│   │   └── forms/          # Form components
│   ├── services/           # API service
│   ├── styles/             # Global CSS
│   └── assets/             # Images, fonts, etc.
└── public/                 # Static assets
```

---

## 13. Navigation Flow

```
ShowcasePage (/)
    ↓
HomePage (/home, /tours)
    ↓
SubSectorPage (/tours/:sectorId)
    ↓
ProductPage (/tours/:sectorId/:subSectorId)
    ↓
TourDetailPage (/tour/:tourId)
    ↓
BookingPage (/booking/:tourId)

Parallel access to:
- SearchPage (/search)
- Static pages (About, Terms, Feedback, etc.)
- Auth pages (Login, Register)
```

---

## 14. Component Interaction Map

```
PageLayout (wrapper for all pages)
├── Header
│   ├── LanguageSelector
│   └── Navigation links
├── Sidebar
│   └── CategoryCard (clickable categories)
├── Main Content
│   └── Page-specific content
│       └── TourCard(s)
│       └── Forms (SearchForm, PassengerForm, CustomerForm)
│       └── Pagination
└── Footer
```

---

## Summary

The ETour application follows a **component-based architecture** with:
1. **Route-driven navigation** for different pages
2. **Hierarchical component structure** with layout wrappers
3. **Modular CSS** for maintainability
4. **Service layer** for API communication
5. **Reusable components** for consistency
6. **Form-based user interactions** for bookings and searches

This structure enables easy scalability, maintainability, and feature additions.
