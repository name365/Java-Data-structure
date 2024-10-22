package github2;

public class Josepfu {

	public static void main(String[] args) {
		//测试构建环形链表和遍历是否正确
		CircleLinkList cingk = new CircleLinkList();
		cingk.addBoy(5);	//加入五个节点
		cingk.shoeBoy();	//显示一下
		
		//测试一下:判断人物出圈是否正确
		cingk.countBoy(1, 42, 5); // 2->4->1->5->3
	}

}
//创建一个单项环形链表
class CircleLinkList{
	//创建一个first节点,当前没有编号
	private Boy first = null;
	//添加新节点,构成一个环形的链表
	public void addBoy(int nums){
		//nums 进行数据校验
		if(nums < 1){
			System.out.println("nums的值不正确");
			return;
		}
		Boy curBoy = null;	//辅助变量,帮助构建环形链表
		//使用循环创建环形链表
		for(int i = 1;i <= nums;i++){
			//根据编号创建节点
			Boy boy = new Boy(i);
			//如果是头节点
			if(i == 1){
				first = boy;
				first.setNext(first); //构成环状
				curBoy = first;	//让curBoy指向第一个节点
			}else{
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}
	}
	
	//遍历当前环形链表
	public void shoeBoy(){
		//判断链表是否为空
		if(first == null){
			System.out.println("链表为空");
			return;
		}
		//因为first头节点不能动,因此创建一个辅助指针完成遍历
		Boy curBoy = first;
		while(true){
			System.out.printf("当前节点的编号 %d\n",curBoy.getId());
			if(curBoy.getNext() == first){	//遍历完成
				break;
			}
			curBoy = curBoy.getNext();	//让curBoy后移 
		}
	}
	
	//根据用户的输入,计算人物出圈的顺序
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年5月23日下午4:45:48
	  * @param startId  表示从第几个人物开始数数
	  * @param countNum 表示数几下
	  * @param nums  表示最初有几个人物在圈中
	 */
	public void countBoy(int startId,int countNum,int nums){
		//先对数据节点进行校验
		if(first == null || startId < 1 || startId > nums){
			System.out.println("数据有误,请重新输入。");
			return;
		}
		//创建一个辅助指针,帮助人物出圈
		Boy helper = first;
		//需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
		while(true){
			if(helper.getNext() == first){	// 说明helper指向最后人物节点
				break;
			}
			helper = helper.getNext();
		}
		//人物报数前，先让 first 和 helper 移动 k - 1次
		for(int j = 0; j < startId - 1; j++) {
			first = first.getNext();
			helper = helper.getNext();
		}		
		//当人物报数时，让first 和 helper 指针同时 的移动 m - 1 次
		//通过循环,知道圈中只有一个节点
		while(true) {
			if(helper == first) { //说明圈中只有一个人物节点
				break;
			}
			//让 first 和 helper 指针同时 的移动 countNum - 1
			for(int j = 0; j < countNum - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//此处first指向的节点，就是要出圈的人物节点
			System.out.printf("人物%d出圈\n", first.getId());
			//此处将first指向的人物节点出圈
			first = first.getNext();
			helper.setNext(first);
		}
		System.out.printf("最后留在圈中的人物的编号:%d \n", first.getId());
	}
}

//创建一个Boy类,表示一个节点
class Boy{
	private int id;	//编号
	private Boy next;	//指向下一个节点,默认为null
	
	public Boy(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}
	
}