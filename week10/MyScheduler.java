package week10;

// MyScheduler 클래스는 일정(작업)을 시간 순으로 정렬하여 등록하고 삭제하는 간단한 스케줄러
// MyLinkedList2<Task>를 기반으로 구현
public class MyScheduler {
	public static class Task implements Comparable<Task>{
		int time;
		String task;

		// 생성자
		Task(int tm, String tk){
			time = tm;
			task = tk;
		}

		// 두 Task가 같은지 비교 (시간과 내용 모두 같아야 같다고 판단)
		@Override
		public boolean equals(Object that) {
			return (this.time==((Task)that).time)&& (this.task.equals(((Task)that).task));
		}

		// Task를 문자열로 출력할 때의 형식 정의
		public String toString() {
			return ""+time + ":00 "+task;
		}

		// 시간 기준으로 Task 비교 (작은 시간이 먼저 오도록)
		public int compareTo(Task that) {
			if (this.time > that.time)
				return 1;
			else if (this.time < that.time)
				return -1;
			else 
				return 0;
		}
	}

	// Task들을 저장할 연결 리스트
	MyLinkedList2<Task> list;

	// 생성자: 빈 리스트로 초기화
	public MyScheduler(){
		list = new MyLinkedList2<Task>();
	}

	// Task를 시간 기준 오름차순으로 리스트에 삽입
	public void register(Task p) {  // add in time-based, ascending order
		if (list.isEmpty()) {
			// 리스트가 비어 있으면 첫 번째 위치에 삽입
			list.add(0, p);
		}
		else {
			// 적절한 삽입 위치 탐색 (compareTo 기준으로 정렬 유지)
			int i = 0;
			while (i < list.size() && list.get(i).compareTo(p) < 0) {  // < 0 은 == -1과 같다
				i++;
			}
			// 찾은 위치에 삽입
			list.add(i, p);
		}
	}

	// 주어진 Task와 일치하는 작업을 제거
	public void done(Task p) {  // remove the given task
		list.remove(p); // equals 메서드를 기준으로 삭제
	}

	// 현재 등록된 전체 스케줄을 출력
	public void showSchedule() {
//		list.showList();
		System.out.println(list.toString());
	}
	
	public static void main(String[] args) {
		MyScheduler ms = new MyScheduler();
		
		ms.showSchedule(); // 초기 상태 출력 (비어 있음)

		// 작업 등록 (자동 정렬됨)
		ms.register(new Task(10, "Seminar"));
		ms.register(new Task(19, "Party"));
		ms.register(new Task(7, "Swimming"));
		ms.showSchedule();

		// 추가 등록 및 삭제
		ms.register(new Task(9, "Tea Meeting"));
		ms.register(new Task(13, "Lunch"));
		ms.done(new Task(7, "Swimming")); // 해당 작업 제거
		ms.showSchedule();

		ms.done(new Task(9, "Tea Meeting"));
		ms.register(new Task(17, "Tennis"));
		ms.showSchedule();

		// 존재하지 않는 작업 제거 시도 및 추가
		ms.done(new Task(9, "Seminar")); // 삭제 안됨 (task 이름이 다름)
		ms.showSchedule();
		ms.done(new Task(10, "Seminar"));
		ms.showSchedule();

	}

}
