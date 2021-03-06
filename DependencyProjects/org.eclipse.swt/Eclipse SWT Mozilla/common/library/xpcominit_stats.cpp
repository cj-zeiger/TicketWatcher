/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *******************************************************************************/

#include "swt.h"
#include "xpcominit_stats.h"

#ifdef NATIVE_STATS

char * XPCOMInit_nativeFunctionNames[] = {
	"GREProperty_1sizeof",
	"GREVersionRange_1sizeof",
	"_1GRE_1GetGREPathWithProperties",
	"_1XPCOMGlueShutdown",
	"_1XPCOMGlueStartup",
};
#define NATIVE_FUNCTION_COUNT sizeof(XPCOMInit_nativeFunctionNames) / sizeof(char*)
int XPCOMInit_nativeFunctionCount = NATIVE_FUNCTION_COUNT;
int XPCOMInit_nativeFunctionCallCount[NATIVE_FUNCTION_COUNT];

#define STATS_NATIVE(func) Java_org_eclipse_swt_tools_internal_NativeStats_##func

JNIEXPORT jint JNICALL STATS_NATIVE(XPCOMInit_1GetFunctionCount)
	(JNIEnv *env, jclass that)
{
	return XPCOMInit_nativeFunctionCount;
}

JNIEXPORT jstring JNICALL STATS_NATIVE(XPCOMInit_1GetFunctionName)
	(JNIEnv *env, jclass that, jint index)
{
	return env->NewStringUTF(XPCOMInit_nativeFunctionNames[index]);
}

JNIEXPORT jint JNICALL STATS_NATIVE(XPCOMInit_1GetFunctionCallCount)
	(JNIEnv *env, jclass that, jint index)
{
	return XPCOMInit_nativeFunctionCallCount[index];
}

#endif
