import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateAgo',
  pure: true,
})
export class DateAgoPipe implements PipeTransform {
  now = new Date().getTime();

  transform(value: any, ...args: unknown[]) {
    let answer;
    const date = new Date(value);
    let time = (this.now - date.getTime()) / 1000;
    if (time < 10) {
      answer = 'posted now';
    } else if (time < 60) { // sent in last minute
      answer = 'posted ' + Math.floor(time) + ' seconds ago';
    } else if (time < 3600) { // sent in last hour
      answer = 'posted ' + Math.floor(time / 60) + ' minutes ago';
    } else if (time < 86400) { // sent on last day
      answer = 'posted ' + Math.floor(time / 3600) + ' hours ago';
    } else { // sent more than one day ago
      answer = 'posted ' + Math.floor(time / 86400) + ' days ago';
    }
    return answer;
  }
}
