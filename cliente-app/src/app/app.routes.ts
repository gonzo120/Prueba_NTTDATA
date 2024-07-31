import { Routes } from '@angular/router';
import { SearchComponent } from './components/search/search.component';
import { SummaryComponent } from './components/summary/summary.component';

export const routes: Routes = [
    { path: '', component: SearchComponent },
    { path: 'summary', component: SummaryComponent}
];
