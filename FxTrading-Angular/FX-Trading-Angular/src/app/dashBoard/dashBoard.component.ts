import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dashBoard',
  templateUrl: './dashBoard.component.html',
  styleUrls: ['./dashBoard.component.css'],
})
export class DashBoardComponent implements OnInit {
  data: any = {};

  constructor(private router: Router) {}

  ngOnInit() {}

  onBook() {
    this.router.navigate(['/bookTrade']);
  }
  onPrint() {
    this.router.navigate(['/printTrade']);
  }
  onExit() {
    // if (confirm('Are your sure')) this.router.navigate(['/exit']);
    Swal.fire({
      title: '<strong>Do you really want to exit</strong>',
      icon: 'info',

      showCloseButton: true,
      showCancelButton: true,
      focusConfirm: false,
      confirmButtonText: '<i class="fa fa-thumbs-up"></i> Yes!',
      confirmButtonAriaLabel: 'Thumbs up, great!',
      cancelButtonText: '<i class="fa fa-thumbs-down">No</i>',
      cancelButtonAriaLabel: 'Thumbs down',
    }).then((result) => {
      if (result.value == true) this.router.navigate(['/exit']);
    });
  }
}
