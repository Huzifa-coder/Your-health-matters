# <div dir="rtl">نبذة عن المشروع</div>
<div dir="rtl">
في الكثير من الأحيان نحتاج إلى عمل شئ مهم باستخدام الهواتف النقالة ولكن عند فتح قفل الهاتف تزدحم علينا التنبيهات ويضيع الوقت وننسى ما نريد فعله.

بالإضافة إلى استخدام الهاتف لوقت طويل دون الانتباه مما يؤثر على صحتنا العامة.

في هذا المشروع سنقوم بحل هذه المشكلة عن طريق توظيف المفاهيم التي تعرفنا عليها لتطوير تطبيق باسم "صحتك تهمنا".

التطبيق يقوم بتنبيه المستخدم عند استخدام الهاتف لأكثر من 25 دقيقة من فتح الشاشة ويتكرر التنبيه لكل 25 دقيقة، عن طريق إضافة تنبيه Notification على الجهاز مع رسالة تشجعه على أخذ استراحة و اقتراح بعض التمارين مثل الوقوف أو المشي.
</div>

# <div dir="rtl">الهدف من هذا المشروع ؟</div> 
<div dir="rtl">
في الدروس الأخيرة تعلمت كيفية استخدام المفاهيم المتعلقة بمهام الخلفية لجعل تطبيق الطقس يقوم بتحديث البيانات تلقائيا في أوقات معينة. في هذا المشروع سوف تتدرب على هذه المهارات مع التركيز على مفاهيم مهام الخلفية مثل WorkManager و Notifications وغيرها.
</div>

# <div dir="rtl">متطلبات المشروع</div>
<div dir="rtl">
يجب أن يحتوي المشروع على المزايا التالية:
<ul>
<li>صفحة رئيسية تعرض كم مرة تم الوصول إلى الحد وتم تنبيه المستخدم اليوم.</li> 
<li> حفظ بيانات المستخدم اليومية باستخدام Room وعرضها في واجهة مختلفة. في هذه الواجهة يتم استخدام مكون يعرف بـ <a href="https://developer.android.com/guide/topics/ui/controls/spinner">Spinner</a> يمكنك التعرف عليه وعلى كيفية استخدامه من خلال 
  <a href="https://developer.android.com/guide/topics/ui/controls/spinner">الرابط</a>
  </li>
<li> تنبيه المستخدم عندما يصل إلى الحد عن طريق إظهار تنبيه باستخدام مفهوم Notification و Notification channel.</li>
<li> استخدام مفهوم WorkManager مع <a href="https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work#constraints">Work constraints</a> لجدولة المهام لتنبيه المستخدم
  </li>
</ul>
</div>

# <div dir="rtl">الشاشة الرئيسية</div>
  <div dir="rtl"><img src="screenshots/screenshot_1.png" heigth="480" width="270"/></div>

# <div dir="rtl">الشاشة الفرعية</div>
<div dir="rtl">
  <img src="screenshots/screenshot_2.png" heigth="480" width="270"/>
<img src="screenshots/screenshot_3.png" heigth="480" width="270"/>
  </div>
